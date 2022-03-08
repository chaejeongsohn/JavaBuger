package service;

import dto.UserMember;

public class UserSessionService {
    private static UserMember userSession;

    public static UserMember getUserSession() {
        return userSession;
    }

    public static void setUserSession(UserMember userSession) {
        UserSessionService.userSession = userSession;
    }
}
