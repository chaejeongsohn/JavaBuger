package dto;

public class UserMember {
    private String userId; // 영문과 숫자 조합
    private String userPw; // 영문과 숫자조합
    private String userName; // 한글
    private int userPhone; // ex)01055558888
    private int userBirthDay;// ex) 19910101(8자리)
    private String userJoinDate; // sysdate

    public UserMember(String userId, String userPassword, String userName, int userPhone, int userBirthDay) {
        this.userId = userId;
        this.userPw = userPassword;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBirthDay = userBirthDay;
    }

    public UserMember(String userId, String userPw, String userName, int userPhone, int userBirthDay, String userJoinDate) {
        this(userId,userPw,userName,userPhone,userBirthDay);
        this.userJoinDate = userJoinDate;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserBirthDay() {
        return userBirthDay;
    }

    public void setUserBirthDay(int userBirthDay) {
        this.userBirthDay = userBirthDay;
    }

    public String getUserJoinDate() {
        return userJoinDate;
    }

    public void setUserJoinDate(String userJoinDate) {
        this.userJoinDate = userJoinDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", userPassword='").append(userPw).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userBirthDay=").append(userBirthDay);
        sb.append(", userJoinDate='").append(userJoinDate).append('\'');
        return sb.toString();
    }
}
