package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import utils.DbUtils;

public class ManagementDAOImpl implements ManagementDAO {
	private Properties proFile = DbUtils.getProFile();
	// private Properties proFile = DbUtils.getProFile();
	@Override
	public int updatePassword(String managerPw) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		 String sql = proFile.getProperty("management.updatePW"); //update
		// management set Manager_PWD = ?
		try {
			con = DbUtils.getConnection();
			//ps = con.prepareStatement("update management set Manager_PWD = ?");
			ps= con.prepareStatement(sql);
			// ?의 개수만큼 순서대로 setXxxx설정 필요
			ps.setString(1, managerPw);

			result = ps.executeUpdate();

		} finally {
			DbUtils.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int updateStoreName(String storeName) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("management.updateManagerPWD"); //update
		// management set Store_Name = ?
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			// ?의 개수만큼 순서대로 setXxxx설정 필요
			ps.setString(1, storeName);

			result = ps.executeUpdate();

		} finally {
			DbUtils.close(con, ps, null);
		}
		return result;
	}

	@Override
	public String checkPassword(String managerPw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		String sql = proFile.getProperty("management.checkManagerPWD"); //"select
		// manager_pwd from management"
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			// ?의 개수만큼 순서대로 setXxxx설정 필요
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
			// System.out.println(result);
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return result;
	}

}