package view.menu;

import controller.PaymentController;
import controller.UserCouponController;
import controller.UserMemberController;
import dto.UserMember;
import exception.NotFoundException;
import service.UserSessionService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserDetailMenuView {
    private static Scanner scanner = new Scanner(System.in);

    public static void memberMenu() throws SQLException, NotFoundException {
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
                    printUserPaymentMenu();
                    break;
                case 4: // 쿠폰조회
                    printUserCouponMenu();
                    break;
                case 5: //이전으로 돌아가기
                    return;
                case 6: // 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
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
                UserMember updateUserMember = new UserMember(UserSessionService.getUserSession());
                printUpdateUserMenu(updateUserMember);
                UserMemberController.updateUser(updateUserMember);
                break;
            case 3: // 회원탈퇴
                System.out.println("정말로 탈퇴하시겠습니까? (Y/N) ");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("Y")) {
                    UserMemberController.deleteUser();
                } else {
                    System.out.println("이전으로 돌아갑니다.");
                    return;
                }
                break;
            case 4: // 이전으로 돌아가기
                return;

            case 5: // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);

        }
    }


    private static void printUpdateUserMenu(UserMember updateUserMember) {
        System.out.println("---------정보 수정 메뉴 -----------");
        System.out.println("1.이름변경 2.폰번호변경 3.비밀번호변경 4.이전으로 돌아가기 5. 프로그램 종료");
        System.out.println("메뉴를 선택하세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());
        //UserMember updateUserMember = new UserMember(UserSessionService.getUserSession());
        switch (menu) {
            case 1: // 이름변경
                System.out.print("새로운 이름은? > ");
                String newName = scanner.nextLine();
                updateUserMember.setUserName(newName);
                break;
            case 2: // 폰번호변경
                System.out.print("새로운 폰번호는? > ");
                int newPhoneNumber = Integer.parseInt(scanner.nextLine());
                updateUserMember.setUserPhone(newPhoneNumber);
                break;
            case 3: // 비밀번호변경
                System.out.print("새로운 비밀번호는? > ");
                String newPassword = scanner.nextLine();
                updateUserMember.setUserPw(newPassword);
                break;
            case 4:
                System.out.println("이전으로 돌아갑니다.");
                return;

            case 5: // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);

        }
    }

    private static void printUserCouponMenu() {
        UserCouponController.selectUserCoupons(UserSessionService.getUserSession().getUserId());
    }

    private static void printUserPaymentMenu() {
        System.out.println("---- 전체 구매내역 ---");
        String userId = UserSessionService.getUserSession().getUserId();
        System.out.println("------------ 나의 구매내역 메뉴 ---------------");
        System.out.println("1. 월별 구매내역 상세조회   2. 구매날짜로 상세조회  3. 전체 구매내역 조회 4. 이전으로 돌아가기 5. 프로그램 종료 ");
        System.out.println("메뉴를 선택하세요 > ");
        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu) {
            case 1: // 월별 구매내역 상세조회
                System.out.println("조회 원하시는 해당 월을 입력하세요 > ");
                int month = Integer.parseInt(scanner.nextLine());
                //PaymentController.selectUserPaymentByMonth(month);
                break;
            case 2: // 구매날짜로 상세조회
                System.out.println("원하시는 조회 날짜를 입력하세요 ex)2022-03-01 > ");
                String paymentDate = scanner.nextLine();
                PaymentController.selectUserPaymentByPaymentDate(userId, paymentDate);
                break;
            case 3: // 전체 구매내역 조회
                PaymentController.selectPaymentByUserId(userId);
                break;
            case 4:
                System.out.println("이전으로 돌아갑니다.");
                return;
            case 5: // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);


        }

    }


}
