package dao;

import dto.UserMember;

import java.sql.SQLException;
import java.util.List;

public interface UserMemberDAO {

    /**
     * userId를 통해 UserMemeber 정보찾기
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    UserMember selectUserInfo(String userId) throws SQLException;

    /**
     * 회원 추가
     *
     * @param userMember
     * @return
     * @throws SQLException
     */
    int insertUser(UserMember userMember) throws SQLException;


    /**
     * 아이디 찾기
     *
     * @param userName
     * @param userPhone
     * @return
     * @throws SQLException
     */
    String selectUserId(String userName, int userPhone) throws SQLException;
    
    /**
     * 비밀번호 찾기
     *
     * @param userId
     * @param userBirthday
     * @return
     * @throws SQLException
     */
    String selectUserPw(String userId, int userBirthday) throws SQLException;

    /**
     * 해당 생일 UserMember들 체크
     *
     * @param userBirthDay
     * @return
     * @throws SQLException
     */
    List<UserMember> selectByBirthday(int userBirthDay) throws SQLException;

    /**
     * 회원 정보 수정
     *
     * @param userMember
     * @return
     * @throws SQLException
     */
    int updateUser(UserMember userMember) throws SQLException;


    /**
     * 회원 삭제
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    int deleteUser(String userId) throws SQLException;
}
