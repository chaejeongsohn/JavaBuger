package service;

import dao.CouponDAO;
import dao.CouponDAOImpl;
import dto.Coupon;

import java.util.List;

public class CouponService {
    CouponDAO couponDAO = new CouponDAOImpl();

    public void insertCoupon(Coupon coupon) {

    }
    public void deleteCoupon(int CouponNumber) {

    }
    public void updateCoupon(Coupon coupon) {

    }

    public List<Coupon> selectCoupons() {

        return null;
    }

    public Coupon selectCouponByNumber(int couponNumber){
        return null;
    }

}
