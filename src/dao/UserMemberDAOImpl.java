package dao;

import dto.UserMember;

import java.sql.SQLException;
import java.util.List;

public class UserMemberDAOImpl implements UserMemberDAO {
    @Override
    public UserMember selectUserInfo(String userId) throws SQLException {
        return null;
    }

    @Override
    public int insertUser(UserMember userMember) throws SQLException {
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
