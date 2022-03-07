package controller;

import dto.UserMember;
import service.UserMemberService;
import view.FailView;


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

    }

    public static void findUserId(String userName, int userPhone) {

    }

    public static void findUserPw(String userId, int userBirthDay) {

    }

    public static void checkExistUserId(String userId) {

    }

    public static void checkUserInfo(String userId) {

    }

    public static void checkByBirthDay(int userBirthday) {

    }

    public static void deleteUser(String userId) {

    }

}
