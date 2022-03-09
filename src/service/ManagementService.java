package service;

import java.sql.SQLException;

import dao.ManagementDAO;
import dao.ManagementDAOImpl;

public class ManagementService {
	ManagementDAO managementDAO = new ManagementDAOImpl();

	public void updatePassword(String managerPw) throws SQLException {
		int result = managementDAO.updatePassword(managerPw);
		if (result == 0)
			throw new SQLException("수정되지 않았습니다.");
	}

	public void updateStoreName(String storeName) throws SQLException {
		int result = managementDAO.updateStoreName(storeName);
		if (result == 0)
			throw new SQLException("수정되지 않았습니다.");
	}

	public boolean checkPassword(String managerPw) throws SQLException {
		String result = managementDAO.checkPassword(managerPw);
		if (result.equals(managerPw)) {
			//System.out.println("비밀번호가 일치합니다");
			return true;

		} else {
			//System.out.println("비밀번호가 불일치 합니다.");
			return false;
		}

	}
}
