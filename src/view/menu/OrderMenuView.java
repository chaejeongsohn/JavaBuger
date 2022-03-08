package view.menu;

import java.util.Scanner;

public class OrderMenuView {
    private static Scanner scanner = new Scanner(System.in);
    public static void orderMenu() {
//        while(true){
//            printOrderMenu();
//            System.out.println("--------- 비회원 주문 화면 ---------------");
//            System.out.println("메뉴를 선택해주세요 > ");
//            int menu = Integer.parseInt(scanner.nextLine());
//            switch (menu) {
//                case 1: // 상품목록
//
//                    break;
//                case 2: // 상품검색
//                    break;
//                case 3:
//                    return;
//            }
//        }
//
//    }
//
//    private static void printOrderMenu(){
//        System.out.println("---------------------");
//        System.out.println("1. 상품목록   2. 상품검색  3.이전으로 돌아가기");
//    }

        //CartMenuView 로 이동하여 주문받기/장바구니담기/결제화면이동 까지 진행
        CartMenuView.cartMenu1();
    }
}
