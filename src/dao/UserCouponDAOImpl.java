package dao;

import dto.Coupon;
import dto.UserCoupon;
import exception.NotFoundException;
import service.CouponService;
import utils.SampleUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import controller.CouponController;

public class UserCouponDAOImpl implements UserCouponDAO {
	 private Properties proFile = SampleUtils.getProFile();
	 private CouponService couponService = new CouponService();
	 CouponDAO couponDao = new CouponDAOImpl();
	 
	 
    @Override
    public int insertUserCoupon(UserCoupon userCoupon) throws SQLException, NotFoundException{
    	Connection con =null;
    	PreparedStatement ps = null;
    	int addno =1;
    	String sql = proFile.getProperty("usercoupon.insert");
    	int result = 0;
    	
    	try {
    		con = SampleUtils.getConnection();
    		//쿠폰에서 쿠폰 찾기
    		Coupon coupon = couponDao.selectCouponByNumber(userCoupon.getCouponNumber());
    		if(coupon==null) {
    			throw new NotFoundException("해당 쿠폰번호를 가진 쿠폰은 없습니다.");
    		}
    		//user쿠폰함에 쿠폰 찾기, 있다면 수량만 1 증가
    		UserCoupon findcoupon = this.selectUserCouponByNumber(userCoupon.getUserId(), coupon.getCouponNumber());
    		if(findcoupon!=null) {
    			this.addUserCoupon(con, findcoupon, addno);
    		}
    		//user쿠폰함에 쿠폰이 없다면 새로 insert
    		ps = con.prepareStatement(sql);
    		ps.setString(1, userCoupon.getUserId());
    		ps.setInt(2, userCoupon.getCouponNumber());
    		ps.setInt(3, addno);
    		    		
    		result = ps.executeUpdate();
    	}finally {
    		SampleUtils.close(con, ps, null);
    	}
        return result;
    }
    
    
    /*user쿠폰 수량 늘리기*/
    public int addUserCoupon(Connection con, UserCoupon usercoupon, int addno) throws SQLException{
    	PreparedStatement ps =null;
    	String sql = proFile.getProperty("usercoupon.updateadd");
    	int result = 0;
    	try {
    		ps =con.prepareStatement(sql);
    		ps.setInt(1, addno);
    		ps.setString(2, usercoupon.getUserId());
    		ps.setInt(3, usercoupon.getCouponNumber());
    		
    		result =ps.executeUpdate();
    		
    	}finally {
    		SampleUtils.close(null, ps, null);
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
    		con = SampleUtils.getConnection();
    		
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, couponNumber);
    		
    		result =ps.executeUpdate();
    	}finally {
    		SampleUtils.close(con, ps, null);
    	}
        return result;
    }

    @Override
    public List<UserCoupon> selectUserCoupons(String userId) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps= null;
    	ResultSet rs =null;
    	List<UserCoupon> usercouponlist = new ArrayList<>();
    	String sql = proFile.getProperty("usercoupon.selectAll");
    	List<Coupon> couponlist = new ArrayList<>();
    	
    	
    	try {
    		con= SampleUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		ps.setString(1, userId);
    		
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			UserCoupon usercoupons = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    			
    		//쿠폰번호에 해당하는 쿠폰정보 가져오기
    		Coupon coupon = couponService.selectCouponByNumber(rs.getInt(1));
    		//쿠폰기한이 만료되었으면 삭제하기
    		boolean checkresult = compareDate(coupon.getCouponExpiration());
    		if(checkresult) {
    		usercouponlist.add(usercoupons);
    		usercoupons.setCouponlist(couponlist);
    		couponlist.add(coupon);
    		}else {
    			deleteUserCoupon(coupon.getCouponNumber());
    		}
    		}
    	}finally {
    		SampleUtils.close(con, ps, rs);
    	}
        return usercouponlist;
    }
    
    /*쿠폰 만료기한 현재날짜랑 비교 메소드*/
    public boolean compareDate(int checkDate){
    	
    	LocalDate today = LocalDate.now();
    	String asStringDate = String.valueOf(checkDate);
    	LocalDate date = LocalDate.parse(asStringDate, DateTimeFormatter.BASIC_ISO_DATE);
    	
    	int result = today.compareTo(date);
    	if(result<0) {return true;}
    	return false;
    	
    	
    	/*작동잘되면 삭제할것
    	 * String todayFormat = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	
    	Date today = new Date(dateFormat.parse(todayFormat).getTime());
    	Date date = new Date(dateFormat.parse(chechDate).getTime());*/
    	
    	
    }
    

    @Override
    public UserCoupon selectUserCouponByNumber(String userId, int couponNumber) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs= null;
    	UserCoupon usercoupon = null;
    	String sql = proFile.getProperty("usercoupon.selectByNo");
    	List<Coupon> couponlist = new ArrayList<>();
    	
    	try {
    		con = SampleUtils.getConnection();
    		ps =con.prepareStatement(sql);
    		ps.setString(1, userId);
    		ps.setInt(2, couponNumber);
    		
    		rs= ps.executeQuery();
    		if(rs.next()) {
    			usercoupon = new UserCoupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    		}
    		//쿠폰번호에 해당하는 쿠폰정보 가져오기
    		Coupon coupon = couponService.selectCouponByNumber(couponNumber);
    		couponlist.add(coupon);
    		
    	}finally {
    		SampleUtils.close(con, ps, rs);
    	}
        return usercoupon;
    }
}
