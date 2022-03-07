package dao;

import dto.UserMember;
import utils.SampleUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMemberDAOImpl implements UserMemberDAO {
    @Override
    public UserMember selectUserInfo(String userId) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs= null;
    	UserMember usermember = null;
    	
    	try {
    		con = SampleUtils.getConnection();
    		ps = con.prepareStatement("select * from usermember where user_id=?");
    		ps.setString(1, userId);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			usermember = new UserMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
    		
    		}
    	}finally {
    		SampleUtils.close(con,ps,rs);
    	}
        return usermember;
    }

    @Override
    public int insertUser(UserMember userMember) throws SQLException {
    	String userId;
    	int result=0;
    	
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	//UserMember usermember = null;
    	
    	
    	try {
	    	con = SampleUtils.getConnection();
			ps = con.prepareStatement("insert into usermember (user_id, user_pw, user_name, user_phone, user_birthday, user_joindate) values (?, ?, ?, ?, ?, sysdate)");
			
			ps.setString(1, userMember.getUserId());
			
			ps.setString(2, userMember.getUserPw());
			
			ps.setString(3, userMember.getUserName());
			
			ps.setInt(4, userMember.getUserPhone());
			
			ps.setInt(5, userMember.getUserBirthDay());
			
			//rs = ps.executeQuery();
			
			result = ps.executeUpdate();
			//System.out.println("result = " + result);
			
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}finally {
    		SampleUtils.close(con, ps, rs);
    	}
        return result;
    }

    @Override
    public String selectUserId(String userName, int userPhone) throws SQLException {
    	String userId = null;
    	
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	
    	String sql = "select user_id from usermember where user_name = ? and user_phone = ? ";
    	try {
    		con = SampleUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		
    		ps.setString(1, userName);
    		ps.setInt(2, userPhone);
    		
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			userId = rs.getString(1);
    		}
    	
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		SampleUtils.close(con, ps, rs);
    	}
    	return userId;
    }
    
    @Override
    public List<UserMember> selectByBirthday(int userBirthDay) throws SQLException {
        return null;
    }

    @Override
    public int updateUser(UserMember userMember) throws SQLException {
    	Connection con = null;
    	PreparedStatement ps = null;
    	int result = 0;
    	
    	String sql = "update usermember set ";
        return 0;
    }

    @Override
    public int deleteUser(String userId) throws SQLException {
        return 0;
    }
}
