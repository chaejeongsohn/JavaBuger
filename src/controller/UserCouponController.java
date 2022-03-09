package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Coupon;
import dto.UserCoupon;
import exception.NotFoundException;
import service.UserCouponService;
import view.EndView;
import view.FailView;
import view.SuccessView;

public class UserCouponController {
    static UserCouponService userCouponService = new UserCouponService();

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
    		EndView.printUserCoupon(usercoupon);
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    	return usercoupon;
    }
}
