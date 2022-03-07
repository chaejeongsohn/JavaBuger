package service;

import dao.UserCouponDAO;
import dao.UserCouponDAOImpl;
import dto.Coupon;
import dto.UserCoupon;

import java.util.List;

public class UserCouponService {

    UserCouponDAO userCouponDAO = new UserCouponDAOImpl();

    public void insertUserCoupon(UserCoupon userCoupon) {
    }

    public void deleteUserCoupon(int userCouponNumber) {
    }


    public List<UserCoupon> selectUserCoupons() {
        return null;
    }

    public UserCoupon selectUSerCouponByNumber(int userCouponNumber) {
        return null;
    }

}
