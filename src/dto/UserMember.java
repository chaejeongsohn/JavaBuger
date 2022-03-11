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

    public UserMember(UserMember userMember){
        this(userMember.userId, userMember.userPw, userMember.userName, userMember.userPhone, userMember.userBirthDay, userMember.userJoinDate);

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
        final StringBuilder sb = new StringBuilder();
        sb.append("아이디 : ").append(userId).append('\n');
        sb.append("패스워드 : ").append(userPw).append('\n');
        sb.append("이름 : ").append(userName).append('\n');
        sb.append("휴대폰번호 : 0").append(userPhone).append('\n');
        sb.append("생일 : ").append(userBirthDay).append('\n');
        sb.append("가입날짜 : ").append(userJoinDate).append('\n');
        return sb.toString();
    }
}
