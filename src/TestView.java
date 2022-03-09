import java.util.List;

import controller.CouponController;
import controller.PaymentController;
import dto.OrderOption;
import dto.OrderProduct;
import dto.Payment;

public class TestView {
	

	
	public static void main(String[] args) {
		
		PaymentController.selectSalseByDate();
		//System.out.println("전체 쿠폰 조회");
		//CouponController.selectCoupons();
		//System.out.println("쿠폰 입력");
		//Coupon coupon = new Coupon(0,"dog02",1010,0);
		//CouponController.insertCoupon(coupon);
		
		/*System.out.println("결제하기--쿠폰 없는 경우");
		Payment pay = new  Payment(0, "gs123",null, 1, 0,0);

		OrderProduct order = new OrderProduct(0, 0, 5, 2);

		OrderOption orderoption = new OrderOption (0, 0, 5);

		pay.getOrderlist().add(order);
		order.getOrderoptionlist().add(orderoption);
		PaymentController.insertPayment(pay);*/

		
		/*Map<String, List<CartProduct>> userCart 가 어떻게 생겼는지.. (test data) :*/
	        /*로그인 유저인 경우:
	            Map<String, List<CartProduct>> userCart = new HashMap<>();
	            userCart.put("test123", null);
	            userCart.put("test123", new ArrayList() {{ add(**CartProduct 객체); add(**CartProduct 객체) … }});
	            CartProduct cp1 = new CartProduct( new Product(DTO 보고 내용채우기) , new ArrayList() {{ add(**ProductOption 객체); add(**ProductOption 객체); … }}
	            CartProduct cp2 = ~
	            CartProduct cp3 = ~
	            …

	       /* 비회원 유저인 경우:
	            위에 'test123' 대신 null 입력*/

	}
	
	

}
