package dao;

import dto.Coupon;
import exception.AddException;
import utils.SampleUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CouponDAOImpl implements CouponDAO {
	private Properties proFile = SampleUtils.getProFile();
	
    @Override
    public int insertCoupon(Coupon coupon) throws SQLException, AddException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	String sql = proFile.getProperty("coupon.insert");
    	int result = 0;
    	
    	try {
    		con = SampleUtils.getConnection();
    		
    		ps = con.prepareStatement(sql);
    		
    		boolean checkDate = this.validationDate(Integer.toString(coupon.getCouponExpiration()));
    		if(checkDate) {
    		ps.setInt(1, coupon.getCouponNumber());
    		ps.setString(2, coupon.getCouponDetail());
    		ps.setInt(3, coupon.getCouponDiscountRate());
    		ps.setInt(4, coupon.getCouponExpiration());
    		
    		result= ps.executeUpdate();
    		}else {
    			throw new AddException("유효하지 않은 날짜입니다.");
    		}
    		
    	}finally {
    		SampleUtils.close(con, ps, null);
    	}
        return result;
    }

    @Override
    public int deleteCoupon(int couponNumber) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	String sql = proFile.getProperty("coupon.delete");
    	int result = 0;
    	
    	try {
    		con = SampleUtils.getConnection();    				

    		ps = con.prepareStatement(sql);
    		ps.setInt(1, couponNumber);
    		
    		result=ps.executeUpdate();
    		
    	}finally {
    		SampleUtils.close(con, ps, null);
    	}
    	 return result;
    	}
    			

    @Override
    public int updateCoupon(Coupon coupon) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps =null;
    	String sql = proFile.getProperty("coupon.update");
    	int result= 0;
    	
    	try {
    		con = SampleUtils.getConnection();
    		
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, coupon.getCouponNumber());
    		ps.setString(2, coupon.getCouponDetail());
    		ps.setInt(3, coupon.getCouponDiscountRate());
    		ps.setInt(4, coupon.getCouponExpiration());
    		
    		result = ps.executeUpdate();
    		
    	}finally{
    		SampleUtils.close(con, ps, null);
    	}
        return result;
    }

    @Override
    public List<Coupon> selectCoupons() throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs= null;
    	List<Coupon> couponlist = new ArrayList<Coupon>();
    	String sql = proFile.getProperty("coupon.selectAll");
    	
    	try {
    		con = SampleUtils.getConnection();
    		ps =con.prepareStatement(sql);
    		System.out.println("왔음");
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			Coupon coupons = new Coupon(
    					rs.getInt(1),
    					rs.getString(2),
    					rs.getInt(3),
    					rs.getInt(4));
    			couponlist.add(coupons);
    		}
    	}finally {
    		SampleUtils.close(con, ps, rs);
    	}
    			
        return couponlist;
    }

    @Override
    public Coupon selectCouponByNumber(int CouponNumber) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs= null;
    	Coupon coupon =null;
    	String sql = proFile.getProperty("coupon.selectByNo");
    	try {
    		con = SampleUtils.getConnection();
    		ps=con.prepareStatement(sql);
    		ps.setInt(1, CouponNumber);
    		
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			coupon = new Coupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}
    	}finally{
    		SampleUtils.close(con, ps, rs);
    	}
        return coupon;
    }
    
    /*쿠폰 날짜형식 확인 메소드*/
    public boolean validationDate(String checkDate) {
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    		dateFormat.setLenient(false);
    		dateFormat.parse(checkDate);
    		return true;
    	}catch(ParseException e) {
    		return false;
    	}
    }
}
