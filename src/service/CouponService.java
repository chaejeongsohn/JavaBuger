package service;

import dao.CouponDAO;
import dao.CouponDAOImpl;
import dto.Coupon;
import exception.AddException;

import java.sql.SQLException;
import java.util.List;

public class CouponService {
    CouponDAO couponDAO = new CouponDAOImpl();

    public void insertCoupon(Coupon coupon) throws SQLException, AddException{
    	int result = couponDAO.insertCoupon(coupon);
    	if(result ==0) throw new SQLException("쿠폰 등록에 실패했습니다.");
    }
    public void deleteCoupon(int CouponNumber) throws SQLException{
    	int result = couponDAO.deleteCoupon(CouponNumber);
    	if(result==0)throw new SQLException("쿠폰 삭제에 실패했습니다.");
    }
    public void updateCoupon(Coupon coupon) throws SQLException{
    	int result = couponDAO.updateCoupon(coupon);
    	if(result==0) throw new SQLException("쿠폰 수정에 실패했습니다.");
    }

    public List<Coupon> selectCoupons() throws SQLException{
    	List<Coupon> list = couponDAO.selectCoupons();
    	if(list ==null || list.size()==0) throw new SQLException("등록된 쿠폰이 없습니다.");
        return list;
    }

    public Coupon selectCouponByNumber(int couponNumber)throws SQLException{
    	Coupon coupon = couponDAO.selectCouponByNumber(couponNumber);
    	if(coupon==null)throw new SQLException(couponNumber+"번의 쿠폰이 없습니다.");
        return coupon;
    }

}
