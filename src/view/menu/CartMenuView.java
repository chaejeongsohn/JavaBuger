package view.menu;
import controller.CartController;

import java.util.Scanner;

public class CartMenuView {
    private static Scanner scanner = new Scanner(System.in);
    /*
    1. 상품검색 & 장바구니 담기
     */
    public static void cartMenu1() {
        while(true) {
            System.out.println("--------- 상품 주문 / 장바구니 화면 (1)---------------");
            printCartMenu1();
            /*
                A: 세트
                B: 버거
                C: 사이드
                D: 음료
             */
            //1. 버거 주문   2. 음료 주문   3. 사이드 주문   4. 세트 주문   5. 장바구니 보기
            System.out.println("메뉴를 선택해주세요 > ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch(menu) {
                case 1:
                    CartController.handleProductOrder('B');
                    break;
                case 2:
                    CartController.handleProductOrder('D');
                    break;
                case 3:
                    CartController.handleProductOrder('C');
                    break;
                case 4:
                    CartController.handleProductOrder('D');
                    break;
                case 5:
                    CartMenuView.cartMenu2(); // 상품주문을 하고 장바구니에 다 담겼다면 장바구니확인/주문이동 화면으로 넘어간다
                default:
                    System.out.println("something went wrong with switch case on cartMenu1()");
            }
        }
    }

    /*
    2. 장바구니 확인 & 구매 진행
     */
    public static void cartMenu2() {
        while(true) {
            System.out.println("--------- 상품 주문 / 장바구니 화면 (2)---------------");
            printCartMenu2();
            //1. 수량 플러스   2. 수량 마이너스    3. 전체 삭제   4. 상품목록으로 돌아가기   5. 주문하기
            System.out.println("메뉴를 선택해주세요 > ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch(menu) {
                case 1:
                    CartController.increaseUserCartQuantity(0);
                    break;
                case 2:
                    CartController.decreaseUserCartQuantity(0);
                    break;
                case 3:
                    CartController.clearUserCart();
                    break;
                case 4:
                    CartMenuView.cartMenu1();
                    break;
                case 5:
                    //toDO: 구매 메뉴로 가는 뷰를 여기다 입력
                    break;
                default:
                    System.out.println("something went wrong with switch case on cartMenu2()");
            }
        }
    }

    /*
    1. 상품검색 & 장바구니 담기
     */
    private static void printCartMenu1() {
        System.out.println("상품을 검색하고 주문하여 장바구니에 담는 화면 입니다.");
        System.out.println(" 1. 버거 주문   2. 음료 주문   3. 사이드 주문   4. 세트 주문   5. 장바구니 보기");
    }

    /*
    2. 장바구니 확인 & 구매 진행
     */
    private static void printCartMenu2() {
        System.out.println("장바구니 내역을 확인하고 수량을 조절하며 결제화면으로 넘어갈 수 있는 화면 입니다.");
        System.out.println("1. 수량 플러스   2. 수량 마이너스    3. 전체 삭제   4. 상품목록으로 돌아가기   5. 주문하기");

    }
}
