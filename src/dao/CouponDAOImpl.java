package dao;

import dto.Coupon;

import java.sql.SQLException;
import java.util.List;

public class CouponDAOImpl implements CouponDAO {
    @Override
    public int insertCoupon(Coupon coupon) throws SQLException {
        return 0;
    }

    @Override
    public int deleteCoupon(int couponNumber) throws SQLException {
        return 0;
    }

    @Override
    public int updateCoupon(Coupon coupon) throws SQLException {
        return 0;
    }

    @Override
    public List<Coupon> selectCoupons() throws SQLException {
        return null;
    }

    @Override
    public Coupon selectCouponByNumber() throws SQLException {
        return null;
    }
}
