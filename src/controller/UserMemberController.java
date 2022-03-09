package controller;

import java.sql.SQLException;
import java.util.List;

import dto.UserMember;
import service.UserMemberService;
import service.UserSessionService;
import view.EndView;
import view.FailView;
import view.SuccessView;
import view.menu.UserDetailMenuView;


public class UserMemberController {
    static UserMemberService userMemberService = new UserMemberService();

    public static void login(String userId, String userPw) {
        try {
            userMemberService.login(userId, userPw);
            UserDetailMenuView.memberMenu();
        } catch (Exception e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void join(UserMember userMember) {
    	String userID = null;
    	String userPw = null;
    	int userPhone = 0;
    	int userBirthDay = 0;
    	
    	try {
    		boolean result = false;

    		result = userMemberService.join(userMember);

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


    public static void updateUser(UserMember userMember) {
    	try {
    		userMemberService.updateUser(userMember);
    		SuccessView.messagePrint("회원정보를 수정하였습니다.");
    	}catch (SQLException e) {
    		e.printStackTrace();
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    public static void deleteUser() {
        try{
            String userId = UserSessionService.getUserSession().getUserId();
            userMemberService.deleteUser(userId);
            SuccessView.messagePrint(userId+" 님의 회원탈퇴가 완료되었습니다.");
        }catch (SQLException e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void showUserInfo(){
        try{
            UserMember userMember = userMemberService.showUserInfo();
            EndView.userMemberPrint(userMember);
        }catch (Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }

}
