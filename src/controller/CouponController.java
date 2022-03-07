package controller;

import java.sql.SQLException;
import java.util.List;

import dto.Coupon;
import exception.AddException;
import service.CouponService;
import view.EndView;
import view.FailView;
import view.SuccessView;

public class CouponController {
    private static CouponService couponService = new CouponService();
    

    public static void insertCoupon(Coupon coupon) {
    	try{
    		couponService.insertCoupon(coupon);
    		SuccessView.messagePrint("쿠폰을 등록했습니다.");
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}catch(AddException ex) {
    		FailView.errorMessage(ex.getMessage());
    	}
    }

    public static void deleteCoupon(int couponNumber) {
    	try {
    		couponService.deleteCoupon(couponNumber);
    		SuccessView.messagePrint("쿠폰을 삭제했습니다.");
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void updateCoupon(Coupon coupon) {
    	try {
    		couponService.updateCoupon(coupon);
    		SuccessView.messagePrint("쿠폰을 수정했습니다.");
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void selectCoupons() {
    	try{
    		List<Coupon> couponlist = couponService.selectCoupons();
    		EndView.printCouponlist(couponlist);
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void selectCouponByNumber(int CouponNumber) {
    	try{
    		Coupon coupon =couponService.selectCouponByNumber(CouponNumber);
    		EndView.printCoupon(coupon);
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}

    }
}
