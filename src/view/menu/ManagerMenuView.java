package view.menu;

import java.util.Scanner;

public class ManagerMenuView {
    private static Scanner scanner = new Scanner(System.in);

    public static void managerMenu() {
        while(true){
            printManagerMenu();
            System.out.println("메뉴를 선택해주세요 > ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch (menu) {
                case 1: // 상품관리
                    break;
                case 2: // 옵션관리
                    break;
                case 3: // 매출관리
                    break;
                case 4:  // 매점관리
                    break;
                case 5: // 프로그램 종료
                    System.exit(0);
            }
        }
    }

    private static void printManagerMenu(){
        System.out.println("---------------------");
        System.out.println("1. 상품관리 2. 옵션관리 3.매출관리 4. 매점관리 5. 프로그램종료" );
    }
}
