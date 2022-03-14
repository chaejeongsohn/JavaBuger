package view.menu;

import controller.CouponController;
import controller.UserCouponController;
import controller.UserMemberController;
import dto.Coupon;
import dto.UserCoupon;
import service.UserSessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerDetailMenuView {
    private static Scanner scanner = new Scanner(System.in);

    public static void printCouponMenu(){
        System.out.println("-----------------------------------");
        System.out.println("1.쿠폰추가  |  2.쿠폰삭제  |  3.쿠폰수정  |  4.쿠폰목록  |  5.사용자에게 쿠폰지급  |  6.이전으로 돌아가기");
        System.out.println("원하시는 기능의 번호를 입력하세요 > ");
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": // 쿠폰추가
                printInsertCouponMenu();
                break;
            case "2": // 쿠폰삭제
                printDeleteCouponMenu();
                break;
            case "3": // 쿠폰수정
                printUpdateCouponMenu();
                break;
            case "4": // 쿠폰 목록
                printAllCoupons();
                break;
            case "5": //쿠폰지급
            	sendCouponToUser();
            case "6":  // 이전으로 돌아가기
                return;
            default:
				System.out.println("번호를 다시 입력해주세요");
        }

    }

    private static void printInsertCouponMenu(){
        System.out.println("--------- 추가할 쿠폰 정보 ----------");
        System.out.print("쿠폰 번호 입력 > ");
        int couponNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("쿠폰 내용 입력 > ");
        String couponDetail = scanner.nextLine();
        System.out.print("쿠폰 할인률 입력 (10%일 경우 10입력) > ");
        int couponDiscountRate = Integer.parseInt(scanner.nextLine());
        System.out.print("쿠폰 만료기한 입력 (숫자만) > ");
        int couponExpiration = Integer.parseInt(scanner.nextLine());
        System.out.println("------------------------------------");
        Coupon coupon = new Coupon(couponNumber, couponDetail,couponDiscountRate, couponExpiration);
        CouponController.insertCoupon(coupon);
    }

    private static void printDeleteCouponMenu(){
        printAllCoupons();
        System.out.print("삭제할 쿠폰번호를 입력하세요 > ");
        int couponNumber = Integer.parseInt(scanner.nextLine());
        CouponController.deleteCoupon(couponNumber);
    }

    private static void printUpdateCouponMenu(){
        printAllCoupons(); // 쿠폰 종류 전체 출력
        System.out.print("수정할 쿠폰번호를 입력하세요 > ");
        int couponNumber = Integer.parseInt(scanner.nextLine());
        Coupon coupon = CouponController.selectCouponByNumber(couponNumber);

        System.out.println("---------쿠폰 수정 메뉴 -----------");
        System.out.println("1.쿠폰 내용 변경 2.쿠폰 할인률 변경 3.쿠폰 만료기한 변경 4. 이전으로 돌아가기");
        System.out.println("메뉴를 선택하세요 > ");
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": // 쿠폰내용변경
                System.out.print("새로운 쿠폰내용은? > ");
                String couponDetail = scanner.nextLine();
                coupon.setCouponDetail(couponDetail);
                break;
            case "2": // 폰번호변경
                System.out.print("새로운 쿠폰 할인률은? > ");
                int couponDiscountRate = Integer.parseInt(scanner.nextLine());
                coupon.setCouponDiscountRate(couponDiscountRate);
                break;
            case "3": // 쿠폰만료기한
                System.out.print("새로운 쿠폰 만료기한은? > ");
                int couponExpiration = Integer.parseInt(scanner.nextLine());
                coupon.setCouponExpiration(couponExpiration);
                break;
            case "4": // 이전으로 돌아가기
                return;
            default:
				System.out.println("번호를 다시 입력해주세요");
        }
        CouponController.updateCoupon(coupon);
    }

    private static void printAllCoupons(){
        System.out.println("--------쿠폰 목록-----------");
        CouponController.selectCoupons();
    }
    
    private static void sendCouponToUser() {
    	System.out.println("---------------쿠폰 지급 방법 선택---------------");
    	System.out.println("1. 전체 사용자에게 쿠폰 지급  2.특정 사용자에게 쿠폰 지급");
    	System.out.println("메뉴를 선택하세요>");
    	String menu = scanner.nextLine();
    	switch(menu) {
    		case "1":
    			printAllCoupons(); // 쿠폰 종류 전체 출력
    			System.out.print("지급할 쿠폰번호를 입력하세요 > ");
    			int couponNumber = Integer.parseInt(scanner.nextLine());
    			UserCouponController.insertUserCouponToAll(couponNumber);
    			break;
    			
    		case "2":
    			UserMemberController.selectAllUser(); //전체 회원 조회
    			System.out.println("쿠폰을 지급할 회원아이디를 입력하세요 > ");
    			String userID = scanner.nextLine();
    			printAllCoupons(); // 쿠폰 종류 전체 출력
    			System.out.print("지급할 쿠폰번호를 입력하세요 > ");
    			int couponNumber2 = Integer.parseInt(scanner.nextLine());
    			UserCoupon usercoupon = new UserCoupon(0, userID, couponNumber2, 0);
    			UserCouponController.insertUserCoupon(usercoupon);
    			break;
    	}
    }
}
