package view.menu;

import controller.CartController;
import controller.PaymentController;
import controller.UserCouponController;
import dto.*;
import service.UserCouponService;
import service.UserSessionService;
import view.EndView;
import view.FailView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderMenuView {
    private static UserCouponService usercouponservice = new UserCouponService();

    //시간 관계로 일단 MVC 구조 적용 X
    private static Scanner scanner = new Scanner(System.in);
    private static String userId = UserSessionService.getUserSession().getUserId();
    private static Map<String, List<CartProduct>> cart = CartController.getUserCart();
    private static List<CartProduct> cartlist = cart.get(userId);

    public static void orderMenu() {
        System.out.println("\n---------- 구매 화면 --------------------");
        
        int paymentPrice = viewCart(userId);
        if(paymentPrice <=0) {
        	System.out.println("주문하실 상품이 없습니다. 우선 상품을 장바구니에 담아주세요.");
        	return;
        }

        // 주문/결제 메뉴
        UserCoupon usingUserCoupon = null;
        System.out.println("-------------------------------------------------");
        System.out.println("|  1. 쿠폰적용    2. 결제하기    3. 장바구니로 돌아가기  |");
        System.out.println("-------------------------------------------------");
        System.out.print("메뉴를 선택해주세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());
        int payresult=0;

        switch (menu) {
            case 1:
                // 쿠폰 적용
                usingUserCoupon = printCouponMenu(userId);

            case 2:
                System.out.println("----------------------------------");
                System.out.println("|  1. 카드. 2. 계좌이체. 3. 현장결제  |");
                System.out.println("----------------------------------");
                System.out.print("원하시는 수단을 선택해주세요 >");
                int paymentMethod = Integer.parseInt(scanner.nextLine());
                if (usingUserCoupon != null) {
                    payresult = startPay(paymentMethod, usingUserCoupon, paymentPrice);//일단 나중에 수정
                } else {
                    payresult=startPay2(paymentMethod, paymentPrice);
                }
                
                if(payresult!=0) {
                	switch (paymentMethod) {
                    case 1:
                        System.out.println("카드로 결제가 완료되었습니다.");
                        break;
                    case 2:
                        System.out.println("계좌이체로 결제가 완료되었습니다.");
                        break;
                    case 3:
                        System.out.println("현장결제로 결제가 완료되었습니다.");
                        break;
                }
                }
                return;

            case 3:
                // 장바구니로 돌아가기

                CartMenuView.cartMenu2();

                break;

            default:
                System.out.println("default");


        }
    }

    private static int startPay2(int paymentMethod, int paymentPrice) {//쿠폰이 null인경우 (쿠폰선택 안한경우)
        Payment pay = new Payment(userId, paymentMethod, paymentPrice, 0);
        //System.out.println("startpayd임");
        int result =0;
        for (CartProduct cartProduct : cartlist) {
            OrderProduct order = new OrderProduct(0, cartProduct.getProduct().getProductNumber(), cartProduct.getQuantity());
            pay.getOrderList().add(order);

            for (ProductOption po : cartProduct.getOptionList()) {
                int optionNumber = po.getOptionNumber();
                OrderOption orderoption = new OrderOption(0, 0, optionNumber);
                order.getOrderOptionList().add(orderoption);
            }
        }

        result = PaymentController.insertPayment(pay);
        CartController.clearUserCart();
        return result;


    }

    public static int startPay(int paymentmethod, UserCoupon usingUserCoupon, int paymentPrice) {
        Payment pay = new Payment(userId, paymentmethod, paymentPrice, usingUserCoupon.getUserCouponNumber());
        int result=0;
        for (CartProduct cartProduct : cartlist) {
            OrderProduct order = new OrderProduct(0, cartProduct.getProduct().getProductNumber(), cartProduct.getQuantity());
            pay.getOrderList().add(order);


            for (ProductOption po : cartProduct.getOptionList()) {
                int optionNumber = po.getOptionNumber();
                OrderOption orderoption = new OrderOption(0, 0, optionNumber);
                order.getOrderOptionList().add(orderoption);
            }
        }

        result=PaymentController.insertPayment(pay);
        CartController.clearUserCart();
        return result;

    }

    public static int viewCart(String userId) {
        int count = cartlist.size();
        int totalPrice = 0;
        System.out.println("---------- 구매 예정 목록: 총 " + count + " 개 ------------");
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + ". ");

            CartProduct cp = cartlist.get(i);
            Product product = cp.getProduct();
            int priceByProduct = product.getProductPrice();
            System.out.print("[상품명] : " + cp.getProduct().getProductName() + " | ");
            System.out.print("[옵션] : ");
            if (cp.getOptionList() != null) {
                for (ProductOption po : cp.getOptionList()) {
                    System.out.print(po.getOptionName() + " ");
                    priceByProduct += po.getOptionPrice();
                }
            }
            System.out.print("   ");
            priceByProduct *= cp.getQuantity();
            totalPrice += priceByProduct;
            System.out.print("[수량] :" + cp.getQuantity() + " | ");
            System.out.print("[상품 가격] :" + priceByProduct);
            System.out.println();
        }
        System.out.print(" [총 가격] :" + totalPrice);
        System.out.println();
        return totalPrice;
    }


    public static UserCoupon printCouponMenu(String userId) {
        int couponNumber = 0;
        
        	UserCouponController.selectUserCoupons(userId);
            System.out.println("사용하실 쿠폰 번호를 입력해주세요 >");
            couponNumber = Integer.parseInt(scanner.nextLine());

       /* } catch (SQLException e) {
            //e.printStackTrace();
            FailView.errorMessage(e.getMessage());
        }*/
        return UserCouponController.selectUserCouponByNumber(userId, couponNumber);


    

}
}
    	

                


