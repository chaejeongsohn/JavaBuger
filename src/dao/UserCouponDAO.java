package dao;

import dto.UserCoupon;

import java.sql.SQLException;
import java.util.List;

public interface UserCouponDAO {

    /**
     * 사용자 쿠폰 지급
     *
     * @param userCoupon
     * @return
     * @throws SQLException
     */
    int insertUserCoupon(UserCoupon userCoupon) throws SQLException;

    /**
     * 사용자 쿠폰 삭제
     *
     * @param userCouponNumber
     * @return
     * @throws SQLException
     */
    int deleteUserCoupon(int userCouponNumber) throws SQLException;

    /**
     * 사용자의 쿠폰리스트
     *
     * @return
     * @throws SQLException
     */
    List<UserCoupon> selectUserCoupons() throws SQLException;

    /**
     * 사용자의 특정쿠폰 찾기(userCouponNumber 이용)
     *
     * @return
     * @throws SQLException
     */
    UserCoupon selectUserCouponByNumber() throws SQLException;

}
