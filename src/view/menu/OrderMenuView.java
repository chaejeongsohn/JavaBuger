package view.menu;

import controller.*;
import dto.*;
import service.UserSessionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderMenuView {

    //시간 관계로 일단 MVC 구조 적용 X
    private static Scanner scanner = new Scanner(System.in);

    public static void orderMenu() {
        System.out.println("\n---------- 구매 화면 --------------------");

        Map<String, List<CartProduct>> userCart = CartController.getUserCart();
        String userId = UserSessionService.getUserSession().getUserId();
        List<CartProduct> cartProducts = userCart.get(userId);
      

        // 장바구니 내역 출력
        //cartcontroller.Viewcart(String userId)
        //checkTotal
        int count = cartProducts.size();
        int totalPrice = 0;
        System.out.println("---------- 구매 예정 목록: 총 " + count + " 개 ------------");
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + ". ");

            CartProduct cp = cartProducts.get(i);
            Product product = cp.getProduct();
            int priceByProduct = product.getProductPrice();
            System.out.print("상품명 : " + cp.getProduct().getProductName() + ", ");
            System.out.print("상품옵션 리스트 : ");
            if (cp.getOptionList() != null) {
                for (ProductOption po : cp.getOptionList()) {
                    System.out.print(po.getOptionName() + " ");
                    priceByProduct += po.getOptionPrice();
                }
            }
            priceByProduct *= cp.getQuantity();
            totalPrice += priceByProduct;
            System.out.print(", 장바구니 상품 현재수량 :" + cp.getQuantity());
            System.out.print(", 장바구니 상품 가격 :" + priceByProduct);
            System.out.println();
        }
        System.out.print("장바구니 상품 총 가격 :" + totalPrice);
        System.out.println();
        // end of 장바구니 내역 출력


        // 주문/결제 메뉴
        int couponNumber = 0;
        System.out.println("1. 쿠폰적용    2. 결재하기    3. 장바구니로 돌아가기");
        System.out.print("메뉴를 선택해주세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());

        switch (menu) {
            case 1:
                // 쿠폰 적용
                couponNumber = userchoiceCoupon(userId);
                break;

            case 2:
                int paymentPrice = totalPrice;

                // 결제 방법 선택
                System.out.println("1. 카드. 2. 계좌이체. 3. 현장결제");
                System.out.print("원하시는 수단을 선택해주세요 >");
                int paymentMethod = Integer.parseInt(scanner.nextLine());

                //주문내역 : (피클제거, 치즈추가) 새우버거 3개, 콜라 1개을 카드로 결재하시겠습니까? (Y/N)
                System.out.println("주문내역 : ");

                // 위 선택된 couponNumber 에 해당하는 쿠폰의 couponDiscountRate()
                if(couponNumber != 0) {
                    int discountRate = CouponController.selectCouponByNumber(couponNumber).getCouponDiscountRate();
                    paymentPrice = ( totalPrice * (100 - discountRate) ) / 100;
                }

                // Payment 테이블에 결제방법 / 쿠폰 할인률 적용
                Payment payment = new Payment(userId, paymentMethod, paymentPrice , couponNumber);

                try {
                    // 위에서 적용된 Payment 객체를 Payment 테이블에 Insert
   
                    
                    List<OrderProduct> orderProductList = new ArrayList<>();
                    for (CartProduct cartProduct : cartProducts) {
                        orderProductList.add(new OrderProduct(paymentNumber, cartProduct.getProduct().getProductNumber(), cartProduct.getQuantity()));
                    }
                    OrderProductController.insertOrderProductList(orderProductList);
                } catch (SQLException e) {
                    System.out.println(e);
                }/////////////////////////////////////////////////////////////////
                
                
                int paymentNumber = PaymentController.insertPayment(payment);
                Payment pay = new Payment(0, paymentMethod, 0, couponNumber);
                
                
                List<OrderProduct> orderProductList = new ArrayList<>();
                for (CartProduct cartProduct : cartProducts) {
                	pay.getOrderlist().add(new OrderProduct(0, cartProduct.getProduct().getProductNumber(), cartProduct.getQuantity()));
                }
                
                for(CartProduct cp : cartProducts) {
                    for(ProductOption po : cp.getOptionList()) {
                        int optionNumber = po.getOptionNumber();

                        //OrderOption DTO 생성 및 DB insert
                        OrderOption orderOption = new OrderOption(optionNumber);
                        try {
                            OrderOptionController.insertOrderOption(orderOption);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                
               
                		
               
               OrderProduct order= new OrderProduct(0, 상품번호, 상품수량);
               OrderOption orderOption = new OrderOption(0, 0, 옵션번호);
               
               order.getOrderoptionlist().add(orderOption);

                //장바구니 내역 중 옵션리스트를 OrderOption 객체로 만들고 OrderOption 테이블에 Insert
                /*
                              ******************* 여기서 문제사항 *******************
                              ******************** 여기서 문제사항 *******************
                       OrderOptionController, OrderOptionService, OrderOptionDAOImpl 에서
                       insertOrderOption() 메소드를 수정 및 완성 해야 될 듯 합니다..
                 */
                for(CartProduct cp : cartProducts) {
                    for(ProductOption po : cp.getOptionList()) {
                        int optionNumber = po.getOptionNumber();

                        //OrderOption DTO 생성 및 DB insert
                        OrderOption orderOption = new OrderOption(optionNumber);
                        try {
                            OrderOptionController.insertOrderOption(orderOption);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

                switch(paymentMethod) {
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


                //toDO: 결제 다 되면 UserCoupon 테이블에서 위 (1)에서 적용된 쿠폰 지우기? 채정님 확인 필요 ex) deleteCoupon(couponNumber)

                break;

            case 3:
                // 장바구니로 돌아가기
                CartMenuView.cartMenu2();
                break;

            default:
                System.out.println("default");
        }
    }

    // 1번 메뉴 : 유저가 보유한 쿠폰들 출력, 유저선택 쿠폰 반환
    public static int userchoiceCoupon(String userId) {
        int couponNumber = 0;
        UserCouponController.selectUserCoupons(userId);
        System.out.print("사용하실 쿠폰 번호를 입력해주세요 > ");
        while (true) {
            int conum = Integer.parseInt(scanner.nextLine());
            UserCoupon usercoupon = UserCouponController.selectUserCouponByNumber(userId, conum);
            couponNumber = usercoupon.getUserCouponNumber();
            break;
        }
        return couponNumber;
    }
}
