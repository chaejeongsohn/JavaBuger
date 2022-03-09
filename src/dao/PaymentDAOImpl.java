package dao;

import dto.OrderOption;
import dto.OrderProduct;
import dto.Payment;
import dto.Product;
import dto.ProductOption;
import service.OrderProductService;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
<<<<<<< Updated upstream
import java.util.List;
=======
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

>>>>>>> Stashed changes

public class PaymentDAOImpl implements PaymentDAO {
	OrderProductService orderservice = new OrderProductService();
	ProductDAO productDAO = new ProductDAOImpl();
	ProductOptionDAOImpl productoptionDAO = new ProductOptionDAOImpl();
    @Override
    public List<Payment> selectPayments() throws SQLException {
        return null;
    }

    @Override
    public Payment selectPaymentByPayNo(int PaymentNumber) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> selectPaymentByUserId(String userId) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> selectPaymentByPaymentDate(String paymentDate) throws SQLException {
        return null;
    }

    @Override
    public int insertPayment(Payment payment) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	String sql = "insert into payment values (PAY_NO_SEQ.nextval, ?,sysdate,?,?,?)";
    	int result =0;
    	
    	try {
    		
    		con = DbUtils.getConnection();
    		con.setAutoCommit(false);
    		
    		ps = con.prepareStatement(sql);
    		ps.setString(1, payment.getUserId());
    		ps.setInt(2, payment.getPaymentMethod());
    		ps.setInt(3, getTotalPrice(payment));
    		ps.setInt(4, payment.getUserCouponNumber());
    		
    		result=ps.executeUpdate();
    		if(result==0) {
    			con.rollback();
    			throw new SQLException("[주문 실패] 주문하지 못 했습니다.");
    		}else {
    			if((orderservice.insertOrderProduct(con, payment))==false) {
    				con.rollback();
    			}
    			con.commit();
    		}
    		
    	}finally {
    		con.commit();
    		DbUtils.close(con, ps, null);
    	}
        return result;
    }
    
    /*총 구매 금액 구하는 메소드*/
    public int getTotalPrice(Payment payment) throws SQLException {
    	List<OrderProduct> orderlist = payment.getOrderList();
       	int total=0;
       	int optionprice =0;
    	
    	for(OrderProduct order: orderlist) {
    		Product products = productDAO.selectProductByProductNumber(order.getOrderProductNo());
    		if(products==null)throw new SQLException("[주문 실패] 상품번호 오류입니다.");
    		optionprice = this.getOptionPrice(order);
    		
    		System.out.println(optionprice);
    		System.out.println(products.getProductName());
    		System.out.println(products.getProductPrice());// 오류??null
    		System.out.println(order.getOrderProductAmount());
    		
    		total += order.getOrderProductAmount() * (products.getProductPrice()+optionprice);
    	}
    	
    	return total;
    }
    /*해당 물건 옵션 금액 구하는 메소드*/
    public int getOptionPrice(OrderProduct order) throws SQLException{
    	List<OrderOption> orderoptionlist = order.getOrderoptionlist();
    	int optionprice=0;
    	for(OrderOption orderoption : orderoptionlist) {
			ProductOption options = productoptionDAO.selectProductOptionByOptionNumber(orderoption.getOptionNumber());	
			optionprice += options.getOptionPrice();
		}
    	return optionprice;
    }

    @Override
    public int deletePayment(int paymentNumber) throws SQLException {
        return 0;
    }

    @Override
    public int updatePayment(Payment payment) throws SQLException {
        return 0;
    }
}
