package dao;

import dto.UserCoupon;

import java.sql.SQLException;
import java.util.List;

public class UserCouponDAOImpl implements UserCouponDAO {
    @Override
    public int insertUserCoupon(UserCoupon userCoupon) throws SQLException {
        return 0;
    }

    @Override
    public int deleteUserCoupon(int userCouponNumber) throws SQLException {
        return 0;
    }

    @Override
    public List<UserCoupon> selectUserCoupons() throws SQLException {
        return null;
    }

    @Override
    public UserCoupon selectUserCouponByNumber() throws SQLException {
        return null;
    }
}
