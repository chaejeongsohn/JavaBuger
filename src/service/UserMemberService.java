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
     * @param userId
     * @param userPw
     * @return
     */
    public UserMember login(String userId, String userPw)throws NotFoundException, SQLException {
        UserMember userMember = userMemberDAO.selectUserInfo(userId);
        if (userMember == null) {
            throw new NotFoundException("아이디를 다시 확안해주세요.");
        }
        // 비밀번호 체크 필요
        return userMember;
    }

    /**
     * 회원가입
     * @param userMember
     */
    public void join(UserMember userMember){

    }

    /**
     * 아이디 찾기
     * @param userName
     * @param userPhone
     * @return
     */
    public String findUserId(String userName, int userPhone){
        return null;
    }

    /**
     * 패스워드 찾기
     * @param userId
     * @param userPhone
     * @return
     */
    public String findUserPw(String userId, int userPhone){
        return null;
    }

    /**
     * 아이디 중복체크
     * @param userId
     * @return
     */
    public boolean checkExistUserId(String userId){
        return false;
    }

    /**
     * 아이디로 사용자정보 찾기
     * @param userID
     * @return
     */
    public UserMember checkUserInfo(String userID){
        return null;
    }

    public List<UserMember> checkByBirthDay(int userBirthDay){
        return null;
    }

    public void updateUserInfo(UserMember userMember){

    }

    public void deleteUser(String UserId){

    }
}
