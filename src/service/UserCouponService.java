package service;

import dao.UserCouponDAO;
import dao.UserCouponDAOImpl;
import dto.UserCoupon;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public class UserCouponService {

    UserCouponDAO userCouponDAO = new UserCouponDAOImpl();

    public void insertUserCoupon(UserCoupon userCoupon)throws SQLException, NotFoundException {
    	int result = userCouponDAO.insertUserCoupon(userCoupon);
    	if(result==0) throw new SQLException("쿠폰이 등록되지 않았습니다.");
    }

    public void deleteUserCoupon(int couponNumber)throws SQLException {
    	int result = userCouponDAO.deleteUserCoupon(couponNumber);
    	if(result ==0)throw new SQLException("쿠폰이 삭제되지 않았습니다.");
    }


    public List<UserCoupon> selectUserCoupons(String userId)throws SQLException {
    	List<UserCoupon> usercoupons = userCouponDAO.selectUserCoupons(userId);
    	if(usercoupons.isEmpty()||usercoupons.size()==0) {
    		throw new SQLException("(적용할 쿠폰이 없습니다.)");
    	}
        return null;
    }

    public UserCoupon selectUSerCouponByNumber(String userId, int couponNumber) throws SQLException{
    	UserCoupon usercoupon =userCouponDAO.selectUserCouponByNumber(userId, couponNumber);
    	if(usercoupon==null) {
    		throw new SQLException("쿠폰[ "+couponNumber+" ]을 가지고 있지 않습니다.");
    	}
        return null;
    }

}
