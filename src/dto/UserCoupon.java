package dto;

import java.util.ArrayList;
import java.util.List;

public class UserCoupon {
    private int userCouponNumber; // 사용자에게 지급된 쿠폰번호
    private String userId; // 사용자 아이디
    private int couponNumber; // 지급된 쿠폰종류 해당 번호
    private int couponAmount; // 쿠폰수량 (ex.5)
    
    private List<Coupon> couponlist = new ArrayList<Coupon>();

    public UserCoupon(int userCouponNumber, String userId, int couponNumber, int couponAmount) {
        this.userCouponNumber = userCouponNumber;
        this.userId = userId;
        this.couponNumber = couponNumber;
        this.couponAmount = couponAmount;
    }

    public int getUserCouponNumber() {
        return userCouponNumber;
    }

    public void setUserCouponNumber(int userCouponNumber) {
        this.userCouponNumber = userCouponNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(int couponNumber) {
        this.couponNumber = couponNumber;
    }

    public int getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(int couponAmount) {
        this.couponAmount = couponAmount;
    }

    public List<Coupon> getCouponlist() {
		return couponlist;
	}

	public void setCouponlist(List<Coupon> coupon) {
		this.couponlist = coupon;
	}

	@Override
    public String toString() {
		final StringBuilder sb = new StringBuilder("");
        sb.append("userCouponNumber=").append(userCouponNumber);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", CouponNumber=").append(couponNumber);
        sb.append(", couponAmount=").append(couponAmount);
        return sb.toString();
    }
}
