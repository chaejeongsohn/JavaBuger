package dao;

import dto.UserMember;
import service.UserSessionService;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserMemberDAOImpl implements UserMemberDAO {
	private Properties proFile = DbUtils.getProFile();
    @Override
    public UserMember selectUserInfo(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserMember usermember = null;
        String sql = proFile.getProperty("usermember.selectID");

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                usermember = new UserMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));

            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return usermember;
    }
    
	@Override
	public UserMember selectUserInfo(int userPhone) throws SQLException {
	       Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        UserMember usermember = null;
	        String sql = proFile.getProperty("usermember.selectPhone");

	        try {
	            con = DbUtils.getConnection();
	            ps = con.prepareStatement(sql);
	            ps.setInt(1, userPhone);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                usermember = new UserMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));

	            }
	        } finally {
	            DbUtils.close(con, ps, rs);
	        }
	        return usermember;
	}
	
    @Override
    public int insertUser(UserMember userMember) throws SQLException {
        String userId;
        int result = 0;
        String sql = proFile.getProperty("usermember.insert");

        Connection con = null;
        PreparedStatement ps = null;
        //UserMember usermember = null;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, userMember.getUserId());
            ps.setString(2, userMember.getUserPw());
            ps.setString(3, userMember.getUserName());
            ps.setInt(4, userMember.getUserPhone());
            ps.setInt(5, userMember.getUserBirthDay());
            //rs = ps.executeQuery();
            
            result = ps.executeUpdate();
        //    System.out.println("result = " + result);

       } catch (SQLException e) {
           e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
       // System.out.println(result);
        return result;
    }

    @Override
    public String selectUserId(String userName, int userPhone) throws SQLException {
        String userId = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("usermember.select");

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, userName);
            ps.setInt(2, userPhone);

            rs = ps.executeQuery();

            if (rs.next()) {
                userId = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return userId;
    }

    @Override
    public String selectUserPw(String userId, int userBirthday) throws SQLException {
    	String userPw = null;
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = proFile.getProperty("usermember.selectPW");

    	try {
    		con = DbUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		ps.setString(1, userId);
    		ps.setInt(2, userBirthday);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			userPw = rs.getString(1);
    		}

    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		DbUtils.close(con, ps, rs);
    	}
    	return userPw;
    }
    
    @Override
    public List<String> selectByBirthday(int userBirthDay) throws SQLException {
        List<String> birthDayUserIdList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = proFile.getProperty("usermember.selectBday");
        try{
            con=DbUtils.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, userBirthDay);
            rs = ps.executeQuery();

            if(rs.next()){
                String userId = rs.getString(1);
                birthDayUserIdList.add(userId);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con, ps, rs);
        }
        return birthDayUserIdList;
    }

    @Override
    public int updateUser(UserMember userMember) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = proFile.getProperty("usermember.update");
        int result = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, UserSessionService.getUserSession().getUserName());
            ps.setInt(2, UserSessionService.getUserSession().getUserPhone());
            ps.setString(3, UserSessionService.getUserSession().getUserPw());
            ps.setString(4, UserSessionService.getUserSession().getUserId());

            result = ps.executeUpdate();
        } finally {
            DbUtils.close(con, ps);
        }
        return result;
    }

    @Override
    public int deleteUser(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps= null;
		String sql = proFile.getProperty("usermember.delete");
		int result = 0;

		try{
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,userId);

			result = ps.executeUpdate();
		}finally {
			DbUtils.close(con,ps);
		}
        return result;
    }
    
    /*전체 유저 리스트(쿠폰 지급할 때 쓰는 메소드)*/
    public List<UserMember> selectAllUser() throws SQLException{
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql =proFile.getProperty("usermember.selectAll");
    	List<UserMember> userlist = new ArrayList<>();
    	
    	try {
    		con = DbUtils.getConnection();
    		ps = con.prepareStatement(sql);
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			UserMember user = new UserMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
    			userlist.add(user);
    		}
    		System.out.println("UserMemberDAOImpl옴");
    	}finally{
    		DbUtils.close(con, ps, rs);
    	}
    	return userlist;
    }
}
