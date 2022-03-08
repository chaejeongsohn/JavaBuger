package controller;

import java.sql.SQLException;

import dto.UserMember;
import service.UserMemberService;
import view.FailView;
import view.SuccessView;


public class UserMemberController {
    static UserMemberService userMemberService = new UserMemberService();

    public static void login(String userId, String userPw) {
        try {
            UserMember userMember = userMemberService.login(userId, userPw);
        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void join(UserMember userMember) {
    	try {
    		userMemberService.join(userMember);
    		SuccessView.messagePrint("회원가입이 완료되었습니다.");
    	} catch (Exception e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void findUserId(String userName, int userPhone) {
    	try {
    		String result = userMemberService.findUserId(userName, userPhone);
    		SuccessView.messagePrint(userName + "님의 아이디는 " + result + "입니다.");
    	}catch(Exception e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void findUserPw(String userId, int userBirthDay) {
    	try {
    		String result = userMemberService.findUserPw(userId, userBirthDay);
    		SuccessView.messagePrint(userId + "님의 비밀번호는 "+ result + " 입니다.");
    	}catch(Exception e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void checkExistUserId(String userId) {

    }

    public static void checkUserInfo(String userId) {

    }

    public static void checkByBirthDay(int userBirthday) {

    }
    
    public static void updateUser(UserMember userMember) {
    	try {
    		userMemberService.updateUser(userMember);
    		SuccessView.messagePrint("회원정보를 수정하였습니다.");
    	}catch (SQLException e) {
    		e.printStackTrace();
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    public static void deleteUser(String userId) {

    }

}
