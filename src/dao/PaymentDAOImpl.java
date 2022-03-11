package dao;

import dto.*;
import service.UserCouponService;
import utils.DbUtils;

import java.sql.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import controller.CouponController;


public class PaymentDAOImpl implements PaymentDAO {
    private Properties proFile = DbUtils.getProFile();

    ProductDAO productDAO = new ProductDAOImpl();
    ProductOptionDAOImpl productoptionDAO = new ProductOptionDAOImpl();
    UserCouponService usercouponservice = new UserCouponService();
    // OrderProductService orderproductService = new OrderProductService();
    OrderProductDAOImpl orderproductDAOimpl = new OrderProductDAOImpl();
    OrderOptionDAOImpl orderOptionDAOimpl = new OrderOptionDAOImpl();
    UserCouponDAOImpl usercouponimpl = new UserCouponDAOImpl();

    @Override
    public List<Ranking> selectSalesranking(String category) throws SQLException {
        List<Product> productlist = selectProduct(category);
        List<Ranking> ranklist = new ArrayList<>();
        int totalamount = 0;
        int totalSales = 0;
        String categoryName = getCategoryname(category);

        for (Product prd : productlist) {
            totalamount = selectOrderAmount(prd.getProductNumber());
            totalSales = totalamount * prd.getProductPrice();
            Ranking ranking = new Ranking(prd.getProductName(), totalSales, categoryName);
            ranklist.add(ranking);
        }

        Collections.sort(ranklist, new Comparator<Ranking>() {
            @Override
            public int compare(Ranking o1, Ranking o2) {
                return o2.getTotalPrice() - o1.getTotalPrice();
            }
        });
        return ranklist;
    }

    /*카테고리에 속하는 상품검색*/
    public List<Product> selectProduct(String category) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.select");
        List<Product> productlist = new ArrayList<>();

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();

            while (rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                Product prd = new Product(no, category, name, price, null);
                productlist.add(prd);
            }

        } finally {
            DbUtils.close(con, ps, rs);
        }

        return productlist;
    }

    /*상품번호로 주문수량 검색하기*/
    public int selectOrderAmount(int productNumber) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectorderAmount");
        int totalamount = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, productNumber);
            rs = ps.executeQuery();


            while (rs.next()) {
                int amount = rs.getInt(1);
                totalamount = totalamount + amount;
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return totalamount;
    }

    /*카테고리 이름 추출 메소드*/
    public String getCategoryname(String categoryNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.getCategoryname");
        String categoryName = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoryName = rs.getString(1);
            }

        } finally {
            DbUtils.close(con, ps, rs);
        }
        return categoryName;
    }

    @Override
    public List<SalesDate> selectSalseByDate() throws SQLException, NullPointerException {
        List<SalesDate> saleslist = new ArrayList<SalesDate>();
        List<Date> paylist = selectDateAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        for (Date salesdate : paylist) {
            int totalSales = selectSalesAllByDate(salesdate);
            String strDate = dateFormat.format(salesdate);
            if (!strDate.isEmpty()) {
                SalesDate sales = new SalesDate(strDate, totalSales);
                saleslist.add(sales);
            }
        }
        return saleslist;
    }

    /*전체 결제일 조회*/
    public List<Date> selectDateAll() throws SQLException, NullPointerException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectDateAll");
        List<Date> originlist = new ArrayList<>();
        List<Date> resultlist = new ArrayList<>();

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate(1);
                if (date != null) {
                    originlist.add(date);
                }

                int originsize = originlist.size();
                for (int i = 0; i < originsize; i++) {
                    if (!resultlist.contains(originlist.get(i))) {
                        resultlist.add(originlist.get(i));
                    }
                    Collections.sort(resultlist);
                }
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return resultlist;
    }


    /*결제일별 결제 금액 조회*/
    public int selectSalesAllByDate(Date date) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectSalesAllByDate");
        int totalSalses = 0;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            rs = ps.executeQuery();

            while (rs.next()) {
                int sales = rs.getInt(1);
                totalSalses = totalSalses + sales;
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return totalSalses;
    }

    @Override
    public int insertPayment(Payment payment) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = proFile.getProperty("payment.insert");
        //"insert into payment() values (PAY_NO_SEQ.nextval, ?,sysdate,?,?,?)"
        int result = 0;

        try {
        	con = DbUtils.getConnection();
            con.setAutoCommit(false);
            System.out.println("--페이옴--");
            int totoalpp =getTotalPrice(payment);

            ps = con.prepareStatement(sql);
            ps.setString(1, payment.getUserId());
            ps.setInt(2, payment.getPaymentMethod());
            ps.setInt(3, totoalpp);
            if (payment.getUserCouponNumber() != 0) {
                int couponresult = usercouponservice.deleteUserCoupon2(con, payment.getUserCouponNumber());
                if (couponresult == 0) {
                    con.rollback();
                    throw new SQLException("[주문 실패] 해당 쿠폰은 유효하지 않은 쿠폰입니다. 주문하지 못 했습니다.");
                }
                
                ps.setInt(4, payment.getUserCouponNumber());
            } else {
                ps.setString(4, null);
            }

            result = ps.executeUpdate();
            if (result == 0) {
                throw new SQLException("[주문 실패] 주문하지 못 했습니다.");
            } else {
                int[] re = orderproductDAOimpl.insertOrderProduct(con, payment.getOrderList());
                for (int i : re) {
                    if (i != 1) {
                        throw new SQLException("[주문 실패] 상품 주문에 오류가 있습니다. 주문하지 못 했습니다.");
                    }
                }
            }
            con.commit();
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            DbUtils.close(con, ps, null);
        }
        return result;
    }
    /*상품 총 구매금액 구하기*/
    public int getTotalPrice(Payment payment) throws SQLException {
    	System.out.println("구매금액옴");
    	int total=0;
    	int optionprice =0;
    	int discountrate =1;
    	List<OrderProduct> orderProductList = payment.getOrderList();
    	for(OrderProduct order: orderProductList) {
    		Product products = productDAO.selectProductByProductNumber(order.getProductNumber());
    		if(products ==null) {throw new SQLException("[주문 실패] 상품번호 오류입니다.");
    		}else {
    		List<OrderOption> orderoptionlist = order.getOrderOptionList();
    		for(OrderOption orderoption : orderoptionlist) {
    			ProductOption options = productoptionDAO.selectProductOptionByOptionNumber(orderoption.getOptionNumber());
                optionprice += options.getOptionPrice();
    			}
    		}

    		total += (order.getOrderProductAmount()*products.getProductPrice())+optionprice;
    	}
    	discountrate =getCouponDC(payment.getUserCouponNumber());
    	total =  total*discountrate;
    	return total;
    }
    /*쿠폰 할인율 구하기*/
    public int getCouponDC(int usercouponNumber) throws SQLException{
    	System.out.println("쿠폰할인율옴");
    	int rate =1;
    	UserCoupon usercoupon =usercouponimpl.selectUsercouponByUCN(usercouponNumber);
    	Coupon coupon =CouponController.selectCouponByNumber(usercoupon.getCouponNumber());
    	rate=coupon.getCouponDiscountRate();
    	return rate;
    	
    }

    /*해당 물건 옵션 금액 구하는 메소드*/
   /* public int getOptionPrice(OrderProduct order) throws SQLException {
        List<OrderOption> orderoptionlist = order.getOrderOptionList();
        int optionprice = 0;
        for (OrderOption orderoption : orderoptionlist) {
            ProductOption options = productoptionDAO.selectProductOptionByOptionNumber(orderoption.getOptionNumber());
            optionprice += options.getOptionPrice();
        }
        return optionprice;
    }*/

    @Override
    public Payment selectPaymentByPayNo(int PaymentNumber) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> selectPayments() throws SQLException {
        return null;
    }


    @Override
    public List<UserTotalPaymentDetail> selectPaymentByUserId(String userId) throws SQLException {
        List<UserTotalPaymentDetail> userTotalPaymentDetailList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectByUserId");
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();


            while (rs.next()) {
                UserTotalPaymentDetail userTotalPaymentDetail = new UserTotalPaymentDetail(rs.getString(1), rs.getInt(2)
                        , rs.getString(3), rs.getInt(4));
                userTotalPaymentDetailList.add(userTotalPaymentDetail);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return userTotalPaymentDetailList;
    }

    public List<UserPaymentDetail> selectUserPaymentByPaymentDate(String userId, String paymentDate) throws
            SQLException {
        List<UserPaymentDetail> userPaymentDetailByDateList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectByPaymentDate");
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, paymentDate);
            rs = ps.executeQuery();


            while (rs.next()) {
                UserPaymentDetail userPaymentDetailByDate = new UserPaymentDetail(
                        rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
                userPaymentDetailByDateList.add(userPaymentDetailByDate);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return userPaymentDetailByDateList;
    }

    public List<UserPaymentDetail> selectUserPayments(String userId) throws SQLException {
        List<UserPaymentDetail> userPaymentDetailByDateList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectUserPayments");
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();


            while (rs.next()) {
                UserPaymentDetail userPaymentDetailByDate = new UserPaymentDetail(
                        rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                userPaymentDetailByDateList.add(userPaymentDetailByDate);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return userPaymentDetailByDateList;
    }

    public String selectUserPaymentLastOrderDate(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("payment.selectUserLastOrderDate");
        String date = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                date = rs.getString(1);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return date;
    }

}
