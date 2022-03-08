package service;

import dao.UserMemberDAO;
import dao.UserMemberDAOImpl;
import dto.UserMember;
import exception.NotFoundException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		// 로그인된 정보 저장하기

		return userMember;
	}

	/**
	 * 회원가입
	 *
	 * @param userMember
	 */
	public boolean join(UserMember userMember) throws NotFoundException, SQLException {

		if(!validID(userMember.getUserId()) || !validPW(userMember.getUserPw()) || !validPhoneNo(userMember.getUserPhone()) || !validBirthday(userMember.getUserBirthDay())) {
			throw new SQLException("회원가입에 실패했습니다.");
		}
		String userId = userMember.getUserId();
		boolean rs = checkExistUserId(userId);

		if (rs = false) {
			int result = userMemberDAO.insertUser(userMember);
			if (result == 0)
				throw new SQLException("회원가입에 실패했습니다.");
		} else {
			userMemberDAO.insertUser(userMember);
			return true;
		}
		return false;
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

		if (result == null)
			throw new SQLException("해당하는 정보가 없습니다.");
		return result;
	}

	/**
	 * 패스워드 찾기
	 *
	 * @param userId
	 * @param userBirthday
	 * @return
	 */
	public String findUserPw(String userId, int userBirthday) throws SQLException {
		String result = userMemberDAO.selectUserPw(userId, userBirthday);

		if (result == null)
			throw new SQLException("해당하는 정보가 없습니다.");
		return result;
	}

	/**
	 * 아이디 중복체크
	 *
	 * @param userId
	 * @return
	 */
	public boolean checkExistUserId(String userId) throws SQLException, NotFoundException {
		UserMember usermember = userMemberDAO.selectUserInfo(userId);
		if (usermember != null) {

			throw new NotFoundException(userId + " 는 이미 존재하는 아이디입니다.");
		} else {
			return false;
		}
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

	public void updateUser(UserMember userMember) throws SQLException {
		int result = userMemberDAO.updateUser(userMember);
		if (result != 1)
			throw new SQLException("수정에 실패하였습니다.");
	}

	public void deleteUser(String userId) throws SQLException {
		int result = userMemberDAO.deleteUser(userId);
		if (result != 1)
			throw new SQLException(userId + " 님의 회원 탈퇴에 실패하였습니다.");
	}

	public UserMember showUserInfo() throws NotFoundException {
		return UserSessionService.getUserSession();
	}

	/**
	 * 아이디 형식 체크
	 * 
	 * @param userID
	 * @return
	 */
	public boolean validID(String userID) {
		Pattern pattern = Pattern.compile("^[a-z0-9]{5,10}$");
		Matcher matcher = pattern.matcher(userID);

		if (!matcher.matches()) {
			System.out.println("유효하지 않는 아이디 형식입니다. 영소문자와 숫자를 포함하여 6~11글자 내외로 입력해주세요.");
			return false;
		}
		System.out.println("성공");
		return true;
	}

	/**
	 * 비밀번호 형식 체크
	 * 
	 * @param userPw
	 * @return
	 */
	public boolean validPW(String userPw) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]{8,15}$");
		Matcher matcher = pattern.matcher(userPw);

		if (matcher.matches()) {
			return true;
		} else {
			System.out.println("유효하지 않는 비밀번호 형식입니다. 영어와 숫자를 포함하여 8~15글자 내외로 입력해주세요.");
			return false;
		}
	}

	/**
	 * 휴대폰 번호 형식 체크
	 * 
	 * @param userPhone
	 * @return
	 */
	public boolean validPhoneNo(int userPhone) {
		String phone = Integer.toString(userPhone);
		
		if( !phone.startsWith("10")) {
			System.out.println("유효하지 않은 번호입니다. 010으로 시작하는 번호를 입력해주세요");
			return false;
		}
		
		if(phone.length() != 10) {
			System.out.println("유효하지 않는 번호입니다. '-'를 제외한 숫자 11글자를 입력해주세요.");
			return false;
		}
		
		return true;
	}

	/**
	 * 생년월일 형식 체크
	 * 
	 * @param userBirthDay
	 * @return
	 */
	public boolean validBirthday(int userBirthDay) {
		String birthday = Integer.toString(userBirthDay);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			dateFormat.setLenient(false); // false일 경우, 입력한 값이 잘못된 형식일 때 오류 발생
			dateFormat.parse(birthday); // 대상 값 포맷에 적용되는지 확인
			return true;
		} catch (Exception e) {
			System.out.println("유효하지 않은 생년월일 형식입니다.");
			return false;
		}
	}
}
