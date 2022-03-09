package dao;

import dto.*;
import service.OrderProductService;
import utils.DbUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PaymentDAOImpl implements PaymentDAO {
    OrderProductService orderservice = new OrderProductService();
    ProductDAO productDAO = new ProductDAOImpl();
    ProductOptionDAOImpl productoptionDAO = new ProductOptionDAOImpl();

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
        String sql = "select prd_no,prd_name,prd_price from product where category_no =?";
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
        String sql = "select order_prd_amount from orderproduct where prd_no =?";
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
        String sql = "select category_name from productcategory where category_no =?";
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
    public List<SalesDate> selectSalseByDate() throws SQLException {
        List<SalesDate> saleslist = new ArrayList<SalesDate>();
        List<Date> paylist = selectDateAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy년 mm월 dd일");

        for (Date salesdate : paylist) {
            int totalSales = selectSalesAllByDate(salesdate);
            String strDate = dateFormat.format(salesdate);
            SalesDate sales = new SalesDate(strDate, totalSales);
            saleslist.add(sales);
        }
        return saleslist;
    }

    /*전체 결제일 조회*/
    public List<Date> selectDateAll() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select pay_date from payment";
        List<Date> paylist = new ArrayList<>();

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate(1);
                paylist.add(date);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return paylist;
    }

    /*결제일별 결제 금액 조회*/
    public int selectSalesAllByDate(Date date) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select pay_price from payment where pay_date =?";
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
        String sql = "insert into payment values (PAY_NO_SEQ.nextval, ?,sysdate,?,?,?)";
        int result = 0;

        try {

            con = DbUtils.getConnection();
            con.setAutoCommit(false);

            String returnCols[] = {"PAY_NO"};
            ps = con.prepareStatement(sql, returnCols);
            ps.setString(1, payment.getUserId());
            ps.setInt(2, payment.getPaymentMethod());
            ps.setInt(3, payment.getPaymentPrice());
            ps.setInt(4, payment.getUserCouponNumber());

//    		result=ps.executeUpdate();
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                con.rollback();
                throw new SQLException("[주문 실패] 주문하지 못 했습니다.");
            } else {
//    			if((orderservice.insertOrderProduct(con, payment))==false) {
//    				con.rollback();
//    			}
                con.commit();
                return rs.getInt(1);
            }

        } finally {
            con.commit();
            DbUtils.close(con, ps, null);
        }
    }

    /*해당 물건 옵션 금액 구하는 메소드*/
    public int getOptionPrice(OrderProduct order) throws SQLException {
        List<OrderOption> orderoptionlist = order.getOrderoptionlist();
        int optionprice = 0;
        for (OrderOption orderoption : orderoptionlist) {
            ProductOption options = productoptionDAO.selectProductOptionByOptionNumber(orderoption.getOptionNumber());
            optionprice += options.getOptionPrice();
        }
        return optionprice;
    }

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
        String sql = "select pay_date, pay_price, prd_name, order_prd_amount\n" +
                "from payment Left outer join orderproduct\n" +
                "on payment.pay_no = orderproduct.pay_no\n" +
                "Left outer join orderoption\n" +
                "on orderproduct.order_prd_no = orderoption.order_prd_no\n" +
                "Left outer join product\n" +
                "on orderproduct.prd_no = product.prd_no\n" +
                "where payment.user_id = ?";
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

    public List<UserPaymentDetailByDate> selectUserPaymentByPaymentDate(String userId, String paymentDate) throws SQLException {
        List<UserPaymentDetailByDate> userPaymentDetailByDateList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select payment.pay_price, payment.pay_no, product.prd_name, orderOption.order_prd_no, productOption.opt_name\n" +
                "from payment Left outer join orderproduct\n" +
                "on payment.pay_no = orderproduct.pay_no\n" +
                "Left outer join orderoption\n" +
                "on orderproduct.order_prd_no = orderoption.order_prd_no\n" +
                "Left outer join product\n" +
                "on orderproduct.prd_no = product.prd_no\n" +
                "Left outer join productOption\n" +
                "on orderOption.opt_no = productOption.opt_no\n" +
                "where payment.user_id = ?" +
                "and payment.pay_date = ?";
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, paymentDate);
            rs = ps.executeQuery();


            while (rs.next()) {
                UserPaymentDetailByDate userPaymentDetailByDate = new UserPaymentDetailByDate(
                        rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
                userPaymentDetailByDateList.add(userPaymentDetailByDate);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return userPaymentDetailByDateList;
    }

}
