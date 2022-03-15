package dao;

import dto.UserCoupon;
import exception.NotFoundException;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserCouponDAOImpl implements UserCouponDAO {
	 private Properties proFile = DbUtils.getProFile();
	 CouponDAO couponDao = new CouponDAOImpl();

	 @Override
	    public int insertUserCoupon(UserCoupon userCoupon) throws SQLException, NotFoundException{
	    	Connection con =null;
	    	PreparedStatement ps = null;
	    	int addNo =1;
	    	String sql = proFile.getProperty("usercoupon.insert");
	    	int result = 0;
	    	
	    	try {
	    		con = DbUtils.getConnection();
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
	    /*쿠폰이 기존에 없어서 새로 등록하면 , insertUserCoupon
	     * 쿠폰이 기존에 있어서 수량만 늘리면 addUserCoupon
	     * 각각 다른 쿼리문이므로 DAO에서 하지 말고 Service에서 처리하자
	     * */
	    
	    
	    /*user쿠폰 수량 늘리기*/
	    public int addUserCoupon(UserCoupon usercoupon, int addNo) throws SQLException{
	    	Connection con=null;
	    	PreparedStatement ps =null;
	    	String sql = proFile.getProperty("usercoupon.updateadd");
	    	int result = 0;
	    	try {
	    		con=DbUtils.getConnection();
	    		ps =con.prepareStatement(sql);
	    		ps.setInt(1, addNo);
	    		ps.setString(2, usercoupon.getUserId());
	    		ps.setInt(3, usercoupon.getCouponNumber());
	    		
	    		result =ps.executeUpdate();
	    		
	    	}finally {
	    		DbUtils.close(con, ps);
	    	}
	    	return result;
	    }

    @Override
    public int deleteUserCoupon(int couponNumber) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	String sql = proFile.getProperty("usercoupon.delete");
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
    	String sql = proFile.getProperty("usercoupon.delete2");
    	int result =0;
    	try {
    		con.setAutoCommit(false);
    		
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, usercouponnumber);
    		result = ps.executeUpdate();
    		
    		UserCoupon usercoupon  = selectUserCouponByUserCoupon(usercouponnumber);
    		if(usercoupon.getCouponAmount()<1) {
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
    public UserCoupon selectUserCouponByUserCoupon( int usercouponnumber) throws SQLException{
    	Connection con= null;
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	String sql = proFile.getProperty("usercoupon.selectNo");
    	UserCoupon usercoupon = null;
    	try {
    		con= DbUtils.getConnection();
    		ps =con.prepareStatement(sql);
    		ps.setInt(1, usercouponnumber);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			usercoupon = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}
    				
    	}finally {
    		DbUtils.close(con, ps, rs);
    	}
    	return usercoupon;
    }

    @Override
    public List<UserCoupon> selectUserCoupons(String userId) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps= null;
    	ResultSet rs =null;
    	List<UserCoupon> userCouponList = new ArrayList<>();
    	String sql = proFile.getProperty("usercoupon.selectAll");

    	try {
    		con= DbUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		ps.setString(1, userId);
    		
    		rs = ps.executeQuery();
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
    	String sql = proFile.getProperty("usercoupon.selectByNo");
    	
    	try {
    		con = DbUtils.getConnection();
    		ps =con.prepareStatement(sql);
    		ps.setString(1, userId);
    		ps.setInt(2, couponNumber);
    		
    		rs= ps.executeQuery();
    		if(rs.next()) {
    			usercoupon = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}	
    	}finally {
    		DbUtils.close(con, ps, rs);
    	}
        return usercoupon;
    }
    
    public UserCoupon selectUsercouponByUCN(int usercouponNumber)throws SQLException{
    	Connection con = null;
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	UserCoupon usercoupon = null;
    	String sql = proFile.getProperty("usercoupon.selectUCN");
    	
    	try {
    		con = DbUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, usercouponNumber);
    		
    		rs= ps.executeQuery();
    		if(rs.next()) {
    			usercoupon = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}	
    	}finally {
    		DbUtils.close(con, ps, rs);
    	}
    	return usercoupon;
    }
}
