package view.menu;

import java.util.Scanner;

public class UserMenuView {
    private static Scanner scanner = new Scanner(System.in);

    public static void userMenu() {
        while (true) {
            printUserMenu();
            System.out.println("메뉴를 골라주세요 > ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch (menu) {
                case 1: // 로그인
                    break;
                case 2: // 회원가입
                    break;
                case 3: // 아이디찾기
                    break;
                case 4: // 비밀번호찾기
                    break;
                case 5: // 프로그램 종료
                    System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("---------회원전용 메뉴---------------");
        System.out.println("1. 로그인   2. 회원가입  3. 아이디찾기  4. 비밀번호찾기 5. 프로그램종료 ");
    }
}
