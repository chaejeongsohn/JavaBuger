package view.menu;

import java.sql.SQLException;
import java.util.Scanner;

import exception.NotFoundException;

public class RoleMenuView {
    private static Scanner sc = new Scanner(System.in);

    public static void roleMenu()  {
        while (true){
            RoleMenuView.printMenu();
            String menu = sc.nextLine();
            switch (menu){
                case "1": // 회원
                    UserMenuView.userMenu();
                    break;
                case "2": // 매니저
                    ManagerMenuView.managerMenu();
                    break;
                case "3": // 프로그램종료
                	System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
 				   System.out.println("번호를 다시 입력해주세요");
            }
        }
    }

    private static void printMenu(){
        System.out.println("---- ★JAVA BURGER★에 오신 것을 환영합니다 ^^ ----");
        System.out.println("1. 회원   |   2. 매니저   |   3. 프로그램 종료");

    }
}
