package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Coupon;
import dto.UserCoupon;
import dto.UserMember;
import exception.NotFoundException;
import service.UserCouponService;
import service.UserMemberService;
import view.EndView;
import view.FailView;
import view.SuccessView;

public class UserCouponController {
    static UserCouponService userCouponService = new UserCouponService();
    static UserMemberService usermemberService = new UserMemberService();

    public static void insertUserCoupon(UserCoupon userCoupon) {
    		try{
    			userCouponService.insertUserCoupon(userCoupon);
    			SuccessView.messagePrint(userCoupon.getUserId()+"님께 쿠폰이 지급되었습니다.");
    		}catch(SQLException e) {
    			FailView.errorMessage(e.getMessage());
    		}catch(NotFoundException ex) {
    			FailView.errorMessage(ex.getMessage());
    		}
    }

    public static void deleteUserCoupon(int couponNumber) {
    		try{
    			userCouponService.deleteUserCoupon(couponNumber);
    			SuccessView.messagePrint("쿠폰[ "+couponNumber+" ]가 삭제되었습니다.");
    		}catch(SQLException e) {
    			FailView.errorMessage(e.getMessage());
    		}
    }
    
    /*한장 사용하는거 */
    /*public boolean deleteUserCoupon2(int couponnumber, int usercouponnumber) {
		try{
			userCouponService.deleteUserCoupon2(usercouponnumber);
			SuccessView.messagePrint("쿠폰함에 있던 쿠폰[ "+couponnumber+" ] 한 장 사용하셨습니다.");
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		return true;
}*/

    public static void selectUserCoupons(String userId) {
    		try{
    			List<UserCoupon> userCouponList = userCouponService.selectUserCoupons(userId);
    			EndView.printUserCouponlist(userId,userCouponList);
    		}catch(SQLException e) {
    			FailView.errorMessage(e.getMessage());
    		}
    		
    }

 
    public static UserCoupon selectUserCouponByNumber(String userId, int couponNumber) {
    	UserCoupon usercoupon = null;
    	try{
    		usercoupon= userCouponService.selectUSerCouponByNumber(userId, couponNumber);
			System.out.println(usercoupon);
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    	return usercoupon;
    }
    /*전체 사용자에게 쿠폰지급*/
	public static void insertUserCouponToAll(int couponNumber) {
		try {
			List<UserMember> userlist =usermemberService.selectAllUser();
			for(UserMember user : userlist) {
				UserCoupon usercoupon = new UserCoupon(0,user.getUserId(), couponNumber, 0);
				insertUserCoupon(usercoupon);
			}
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
}
