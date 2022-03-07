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
    	
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs= null;
    	UserMember usermember = null;

    	try {
	    	con = DbUtil.getConnection();
			ps = con.prepareStatement("insert into board (userid, userpw, username, userphone, userbirthday, userjoindate) values (?, ?, ?, ?, ?, sysdate)");
			
			ps.setString(1, usermember.);
			rs = ps.executeQuery();
			
			ps.setString(2, userPassword);
			rs = ps.executeQuery();
			
			ps.setString(3, userName);
			rs = ps.executeQuery();
			
			ps.setInt(4, userPhone);
			rs = ps.executeQuery();
			
			ps.setInt(5, userBirthDay);
			rs = ps.executeQuery();
			
    	}finally {
    		
    	}
        return 0;
    }

    @Override
    public String selectUserId(String userName, int userPhone) throws SQLException {
        return null;
    }

    @Override
    public List<UserMember> selectByBirthday(int userBirthDay) throws SQLException {
        return null;
    }

    @Override
    public int updateUser(UserMember userMember) throws SQLException {
        return 0;
    }

    @Override
    public int deleteUser(String userId) throws SQLException {
        return 0;
    }
}
