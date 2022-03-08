package service;

import dao.UserMemberDAO;
import dao.UserMemberDAOImpl;
import dto.UserMember;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public class UserMemberService {
    UserMemberDAO userMemberDAO = new UserMemberDAOImpl();


    /**
     * 로그인
     *
     * @param userId
     * @param userPw
     * @return
     */
    public UserMember login(String userId, String userPw) throws NotFoundException, SQLException {
        UserMember userMember = userMemberDAO.selectUserInfo(userId);
        if (userMember == null) {
            throw new NotFoundException("아이디를 다시 확인해주세요.");
        }

        // 비밀번호 체크 필요
        if (!userPw.equals(userMember.getUserPw())) {
            throw new NotFoundException("비밀번호가 일치하지 않습니다.");
        } else {
            System.out.println("로그인 되었습니다.");
            UserSessionService.setUserSession(userMember);
        }

        //로그인된 정보 저장하기

        return userMember;
    }

    /**
     * 회원가입
     *
     * @param userMember
     */
    public void join(UserMember userMember) throws SQLException {
    	int result = userMemberDAO.insertUser(userMember);
    	if(result==0) throw new SQLException("회원가입에 실패했습니다.");
    	}

    /**
     * 아이디 찾기
     *
     * @param userName
     * @param userPhone
     * @return
     */
    public String findUserId(String userName, int userPhone) throws SQLException {
    	String result = userMemberDAO.selectUserId(userName, userPhone);
    	
    	if(result == null) throw new SQLException("해당하는 정보가 없습니다.");
        return result;
    }

    /**
     * 패스워드 찾기
     *
     * @param userId
     * @param userBirthday
     * @return
     */
    public String findUserPw(String userId, int userBirthday) throws SQLException{
    	String result = userMemberDAO.selectUserPw(userId, userBirthday);
    
    	if(result == null) throw new SQLException("해당하는 정보가 없습니다.");
        return result;
    }

    /**
     * 아이디 중복체크
     *
     * @param userId
     * @return
     */
    public boolean checkExistUserId(String userId) {
        return false;
    }

    /**
     * 아이디로 사용자정보 찾기
     *
     * @param userID
     * @return
     */
    public UserMember checkUserInfo(String userID) {
        return null;
    }

    public List<UserMember> checkByBirthDay(int userBirthDay) {
        return null;
    }

    public void updateUser(UserMember userMember) throws SQLException{
    	int result = userMemberDAO.updateUser(userMember);
    	if(result != 1) throw new SQLException("수정에 실패하였습니다.");
    }

    public void deleteUser(String userId) throws SQLException{
        int result = userMemberDAO.deleteUser(userId);
        if(result != 1) throw new SQLException(userId+" 님의 회원 탈퇴에 실패하였습니다.");
    }

    public UserMember showUserInfo() throws NotFoundException {
        return UserSessionService.getUserSession();
    }
}
