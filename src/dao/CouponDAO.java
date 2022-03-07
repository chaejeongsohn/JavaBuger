package dao;

import dto.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface CouponDAO {
    /**
     * 매니저가 쿠폰종류 추가
     *
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon) throws SQLException;

    /**
     * 매니저가 쿠폰 삭제
     *
     * @param couponNumber
     * @return
     */
    int deleteCoupon(int couponNumber) throws SQLException;


    /**
     * 매니저가 쿠폰 수정
     *
     * @param coupon
     * @return
     * @throws SQLException
     */
    int updateCoupon(Coupon coupon) throws SQLException;

    /**
     * 매니저가 쿠폰 조회
     *
     * @return
     * @throws SQLException
     */
    List<Coupon> selectCoupons() throws SQLException;


    /**
     * 쿠폰번호로 특정 쿠폰 찾기
     *
     * @return
     * @throws SQLException
     */
    Coupon selectCouponByNumber(int CouponNumber) throws SQLException;
}
