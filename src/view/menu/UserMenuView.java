package view.menu;

import java.util.Scanner;

import controller.UserMemberController;
import dao.UserMemberDAOImpl;
import dto.UserMember;
import service.UserMemberService;

public class UserMenuView {
    private static Scanner scanner = new Scanner(System.in);

    public static void userMenu() {
        while (true) {
            printUserMenu();
            System.out.println("메뉴를 골라주세요 > ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch (menu) {
                case 1: // 로그인
                    printLogin();

                    //CartMenuView 로 이동하여 주문받기/장바구니담기/결제화면이동 까지 진행
                    CartMenuView.cartMenu1();
                    break;
                case 2: // 회원가입
                	printJoin(); 
                    break;
                case 3: // 아이디찾기
                	printUserID();
                    break;
                case 4: // 비밀번호찾기
                	printUserPW();
                    break;
                case 5: // 프로그램 종료
                    System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("--------- 회원 로그인 화면 ---------------");
        System.out.println("1. 로그인   2. 회원가입  3. 아이디찾기  4. 비밀번호찾기 5. 프로그램종료 ");
    }

    private static void printLogin(){
        System.out.print("아이디를 입력하세요 > ");
        String inputId = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요 > ");
        String inputPw = scanner.nextLine();

        UserMemberController.login(inputId, inputPw);
    }
    
    private static void printJoin() {
    	System.out.print("사용하실 아이디를 입력하세요 > ");
    	String inputId = scanner.nextLine();
    	System.out.print("사용하실 비밀번호를 입력하세요 > ");
    	String inputPw = scanner.nextLine();
    	System.out.print("이름을 입력하세요 > ");
    	String inputName = scanner.nextLine();
    	System.out.print(" '-'를 제외한 핸드폰 번호를 입력해주세요 > ");
    	int inputPhone = Integer.parseInt(scanner.nextLine());
    	System.out.print("생년월일을 입력하세요 ex.19940416 > ");
    	int inputBirthday =Integer.parseInt(scanner.nextLine());
    	
    	UserMember userMember = new UserMember(inputId, inputPw, inputName, inputPhone, inputBirthday);
    	UserMemberController.join(userMember);
    }
    
    private static void printUserID() {
    	System.out.print("이름을 입력하세요 > ");
    	String inputName = scanner.nextLine();
    	System.out.print("'-'를 제외한 핸드폰 번호를 입력해주세요 > ");
    	int inputPhone = Integer.parseInt(scanner.nextLine());
    	
    	UserMemberController.findUserId(inputName, inputPhone);
    }
    
    private static void printUserPW() {
    	System.out.print("아이디를 입력하세요 > ");
    	String inputId = scanner.nextLine();
    	System.out.print("생년월일 8글자를 입력해주세요 ex.19951105 > ");
    	int inputBirthday = Integer.parseInt(scanner.nextLine());
    	
    	UserMemberController.findUserPw(inputId, inputBirthday);
    }
}
