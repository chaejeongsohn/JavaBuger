package dao;

import java.sql.SQLException;

public interface ManagementDAO {

	/**
	 * 비밀번호 수정
	 *
	 * @param managerPw
	 * @return
	 * @throws SQLException
	 */
	int updatePassword(String managerPw) throws SQLException;

	/**
	 * 매장 이름 수정
	 *
	 * @param storeName
	 * @return
	 * @throws SQLException
	 */
	int updateStoreName(String storeName) throws SQLException;

	/**
	 * 비밀번호 검증
	 *
	 * @param managerPw
	 * @return
	 * @throws SQLException
	 */
	String checkPassword(String managerPw) throws SQLException;

}
