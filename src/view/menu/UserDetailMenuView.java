package view.menu;

import controller.UserMemberController;
import service.UserSessionService;

import java.util.Scanner;

public class UserDetailMenuView {
    private static Scanner scanner = new Scanner(System.in);

    public static void memberMenu() {
        while (true) {
            System.out.println("------------ 회원 전용 메뉴 ---------------");
            System.out.println("1. 주문하기   2. 나의정보  3. 구매내역  4. 쿠폰조회 5.이전으로 돌아가기 6. 프로그램종료");
            System.out.println("메뉴를 선택하세요 > ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch (menu) {
                case 1: // 주문하기
                    //CartMenuView 로 이동하여 주문받기/장바구니담기/결제화면이동 까지 진행
                    CartMenuView.cartMenu1();
                    break;
                case 2: // 나의정보
                    printUserInfoMenu();
                    break;
                case 3: // 구매내역
                    break;
                case 4: // 쿠폰조회
                    break;
                case 5: //이전으로 돌아가기
                    return;
                case 6: // 프로그램 종료
                    System.exit(0);
            }
        }
    }


    private static void printUserInfoMenu() {
        System.out.println("------------ 나의 정보 메뉴 ---------------");
        System.out.println("1. 나의상세정보   2. 정보수정  3. 회원탈퇴  4. 이전으로 돌아가기 5. 프로그램 종료 ");
        System.out.println("메뉴를 선택하세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu) {
            case 1: // 정보확인하기
                UserMemberController.showUserInfo();
                break;
            case 2: // 정보수정
                printUpdateUserMenu();
                UserMemberController.updateUser(UserSessionService.getUserSession());
                break;
            case 3: // 회원탈퇴
                System.out.println("정말로 탈퇴하시겠습니까? (Y/N) ");
                String answer = scanner.nextLine();
                if(answer.toUpperCase().equals("Y")){
                    UserMemberController.deleteUser();
                }else{
                    System.out.println("이전으로 돌아갑니다.");
                    return;
                }
                break;
            case 4: // 이전으로 돌아가기
                return;

            case 5: // 프로그램 종료
                System.exit(0);

        }
    }


    private static void printUpdateUserMenu(){
        System.out.println("---------정보 수정 메뉴 -----------");
        System.out.println("1.이름변경 2.폰번호변경 3.비밀번호변경 4. 이전으로 돌아가기");
        System.out.println("메뉴를 선택하세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu) {
            case 1: // 이름변경
                System.out.print("새로운 이름은? > ");
                String newName = scanner.nextLine();
                UserSessionService.getUserSession().setUserName(newName);
                break;
            case 2: // 폰번호변경
                System.out.print("새로운 폰번호는? > ");
                int newPhoneNumber = Integer.parseInt(scanner.nextLine());
                UserSessionService.getUserSession().setUserPhone(newPhoneNumber);
                break;
            case 3: // 비밀번호변경
                System.out.print("새로운 비밀번호는? > ");
                String newPassword = scanner.nextLine();
                UserSessionService.getUserSession().setUserPw(newPassword);
                break;
            case 4: // 이전으로 돌아가기
                return;
        }
    }
}
