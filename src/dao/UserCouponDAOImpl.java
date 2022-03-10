package dao;

import dto.Coupon;
import dto.UserCoupon;
import exception.NotFoundException;
import service.CouponService;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserCouponDAOImpl implements UserCouponDAO {
	 private Properties proFile = DbUtils.getProFile();
	 private CouponService couponService = new CouponService();
	 CouponDAO couponDao = new CouponDAOImpl();

    @Override
    public int insertUserCoupon(UserCoupon userCoupon) throws SQLException, NotFoundException{
    	Connection con =null;
    	PreparedStatement ps = null;
    	int addNo =1;
		String sql = "insert into usercoupon values(USER_COUPON_NO_SEQ.NEXTVAL,?,?,?)";
    	//String sql = proFile.getProperty("usercoupon.insert");
    	int result = 0;
    	
    	try {
    		con = DbUtils.getConnection();
    		//쿠폰에서 쿠폰 찾기
    		Coupon coupon = couponDao.selectCouponByNumber(userCoupon.getCouponNumber());
    		if(coupon==null) {
    			throw new NotFoundException("해당 쿠폰번호를 가진 쿠폰은 없습니다.");
    		}
    		//user쿠폰함에 쿠폰 찾기, 있다면 수량만 1 증가
    		UserCoupon findCoupon = this.selectUserCouponByNumber(userCoupon.getUserId(), coupon.getCouponNumber());
    		if(findCoupon!=null) {
    			this.addUserCoupon(con, findCoupon, addNo);
    		}
    		//user쿠폰함에 쿠폰이 없다면 새로 insert
    		ps = con.prepareStatement(sql);
    		ps.setString(1, userCoupon.getUserId());
    		ps.setInt(2, userCoupon.getCouponNumber());
    		ps.setInt(3, addNo);
    		    		
    		result = ps.executeUpdate();
    	}finally {
    		DbUtils.close(con, ps);
    	}
        return result;
    }
    
    
    /*user쿠폰 수량 늘리기*/
    public int addUserCoupon(Connection con, UserCoupon usercoupon, int addNo) throws SQLException{
    	PreparedStatement ps =null;
		String sql = "update usercoupon set coupon_amount=coupon_amount+? where user_id=? and coupon_no=?";
    	//String sql = proFile.getProperty("usercoupon.updateadd");
    	int result = 0;
    	try {
    		ps =con.prepareStatement(sql);
    		ps.setInt(1, addNo);
    		ps.setString(2, usercoupon.getUserId());
    		ps.setInt(3, usercoupon.getCouponNumber());
    		
    		result =ps.executeUpdate();
    		
    	}finally {
    		DbUtils.close(null, ps);
    	}
    	return result;
    }

    @Override
    public int deleteUserCoupon(int couponNumber) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
		String sql = "delete from usercoupon where coupon_no = ?";
    	//String sql = proFile.getProperty("usercoupon.delete");
    	int result =0;
    	try {
    		con = DbUtils.getConnection();
    		
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, couponNumber);
    		
    		result =ps.executeUpdate();
    	}finally {
    		DbUtils.close(con, ps);
    	}
        return result;
    }

    /*한장 사용하는거 */
    @Override
    public int deleteUserCoupon2(Connection con, int usercouponnumber) throws SQLException {
    	PreparedStatement ps = null;
    	String sql = "update usercoupon set coupon_amount = coupon_amount-1 where user_coupon_no = ?";
    	int result =0;
    	try {
    		con.setAutoCommit(false);
    		
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, usercouponnumber);
    		result = ps.executeUpdate();
    		
    		UserCoupon usercoupon  = selectUserCouponByUserCoupon(con, usercouponnumber);
    		if(usercoupon.getCouponAmount()==0) {
    			con.rollback();
    			throw new SQLException("사용 가능한 쿠폰 수량이 없습니다.");
    		}else {
			con.commit();
    		}	
    	}finally {
    		DbUtils.close(null, ps);
    	}
    	return result;
    }
    
    /*usercoupon_no 정보 가져오기*/
    public UserCoupon selectUserCouponByUserCoupon(Connection con, int usercouponnumber) throws SQLException{
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	String sql = "select*from usercoupon where user_coupon_no = ?";
    	UserCoupon usercoupon = null;
    	try {
    		ps =con.prepareStatement(sql);
    		ps.setInt(1, usercouponnumber);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			usercoupon = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}
    				
    	}finally {
    		DbUtils.close(null, ps, rs);
    	}
    	return usercoupon;
    }

    @Override
    public List<UserCoupon> selectUserCoupons(String userId) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps= null;
    	ResultSet rs =null;
    	List<UserCoupon> userCouponList = new ArrayList<>();
		String sql =  "select * from usercoupon where user_id= ? order by coupon_no";
    	//String sql = proFile.getProperty("usercoupon.selectAll");

    	try {
    		con= DbUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		ps.setString(1, userId);
    		
    		rs = ps.executeQuery();//쿠폰 조회하고 만료날짜 체크해야 하는데 없어짐
    		while(rs.next()) {
    			UserCoupon userCoupons = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    			userCouponList.add(userCoupons);
    		}
    	}finally {
    		DbUtils.close(con, ps, rs);
    	}
        return userCouponList;
    }
    

    

    @Override
    public UserCoupon selectUserCouponByNumber(String userId, int couponNumber) throws SQLException { //어디서도 쓰지 않는 코드 같음
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs= null;
    	UserCoupon usercoupon = null;
		String sql = "select*from usercoupon where user_id = ? and coupon_no =?";
    	//String sql = proFile.getProperty("usercoupon.selectByNo");
    	List<Coupon> couponlist = new ArrayList<>();
    	
    	try {
    		con = DbUtils.getConnection();
    		ps =con.prepareStatement(sql);
    		ps.setString(1, userId);
    		ps.setInt(2, couponNumber);
    		
    		rs= ps.executeQuery();
    		if(rs.next()) {
    			usercoupon = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}
    		//쿠폰번호에 해당하는 쿠폰정보 가져오기
    		Coupon coupon = couponService.selectCouponByNumber(couponNumber); //connection도 넘겨받아야 하지 않나???
    		couponlist.add(coupon);
    		
    	}finally {
    		DbUtils.close(con, ps, rs);
    	}
        return usercoupon;
    }
}
