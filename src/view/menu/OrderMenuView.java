package view.menu;

import controller.CartController;
import controller.CouponController;
import controller.OrderProductController;
import controller.PaymentController;
import dto.*;
import service.UserSessionService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderMenuView {


    private static Scanner scanner = new Scanner(System.in);

    public static void orderMenu() {
        System.out.println("\n---------- 구매 화면 --------------------");

        Map<String, List<CartProduct>> userCart = CartController.getUserCart();
        String userId = UserSessionService.getUserSession().getUserId();
        List<CartProduct> cartProducts = userCart.get(userId);

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

        System.out.println("1. 쿠폰적용    2. 결재하기    3. 장바구니로 돌아가기");
        System.out.print("메뉴를 선택해주세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu) {
            case 1:
                // 쿠폰 나열
                // CouponController.displayCoupons();
                // 쿠폰 선택 및 적용
                // CouponController.applyCoupon();
                break;
            case 2:
                // 결제 방법 선택
                PaymentController.selectPayments();
                int paymentMethod = 0;
                Payment payment = new Payment(userId, paymentMethod, totalPrice, 1);
                try {
                    int paymentNumber = PaymentController.insertPayment(payment);
                    List<OrderProduct> orderProductList = new ArrayList<>();
                    for (CartProduct cartProduct : cartProducts) {
                        orderProductList.add(new OrderProduct(paymentNumber, cartProduct.getProduct().getProductNumber(), cartProduct.getQuantity()));
                    }
                    OrderProductController.insertOrderProductList(orderProductList);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 3:
                // 장바구니로 돌아가기
                CartMenuView.cartMenu2();
            default:
                System.out.println("default");
        }
    }
}
