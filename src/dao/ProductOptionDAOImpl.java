package dao;

import dto.ProductOption;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOptionDAOImpl {

	// @Override
	public List<ProductOption> selectProductOptions() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductOption> productOptionList = new ArrayList<>();
		// toDO: sql 은 resources/sql.properties 파일에 집어넣기
		String sql = "select * from productoption";

		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ProductOption productOption = new ProductOption(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4));
				productOptionList.add(productOption);
			}
		} finally {
			DbUtils.close(con, ps, rs);
		}

		return productOptionList;
	}

	// @Override
	public ProductOption selectProductOptionByOptionNumber(int productOptionNumber) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductOption productOption = null;
		// toDO: sql 은 resources/sql.properties 파일에 집어넣기
		String sql = "select * from productoption where opt_no=?";

		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, productOptionNumber);
			rs = ps.executeQuery();

			if (rs.next()) {
				productOption = new ProductOption(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} finally {
			DbUtils.close(con, ps, rs);
		}

		return productOption;
	}

	// @Override
	public int insertProductOption(ProductOption productOption) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		// toDO: sql 은 resources/sql.properties 파일에 집어넣기
		String sql = "insert into productoption(opt_no, category_no, opt_name, opt_price) values(opt_no_seq.nextval, ?, ?, ?)";
		int result = 0;
		try {
			con = DbUtils.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setString(1, productOption.getCategoryNumber());
			ps.setString(2, productOption.getOptionName());
			ps.setInt(3, productOption.getOptionPrice());

			result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException(
						"에러: 상품옵션이 입력되지 않았습니다. / public int insertProductOption(ProductOption productOption)");
			}

		} finally {
			con.commit();
			DbUtils.close(con, ps, null);
		}

		return result;
	}

	/*
	 * 파라미터 로 받는 productOption 의 optionNumber, categoryNumber, optionName,
	 * optionPrice 를 추출하여 DB 에 저장된 productionOption 중 같은 optionNumber 가진 튜플을 찾아 업데이트
	 * 해준다.
	 */
	// @Override
	public int updateProductOption(ProductOption productOption) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		// toDO: sql 은 resources/sql.properties 파일에 집어넣기
		String sql = "update productoption set category_no=?, opt_name=?, opt_price=? where opt_no=?";
		int result = 0;

		try {
			con = DbUtils.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setString(1, productOption.getCategoryNumber());
			ps.setString(2, productOption.getOptionName());
			ps.setInt(3, productOption.getOptionPrice());
			ps.setInt(4, productOption.getOptionNumber());

			result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException(
						"에러: 상품옵션이 업데이트 되지 않았습니다. / public int updateProductOption(ProductOption productOption)");
			}
		} finally {
			con.commit();
			DbUtils.close(con, ps, null);
		}

		return result;
	}

	// @Override
	public int deleteProductOption(int productOptionNumber) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		// toDO: sql 은 resources/sql.properties 파일에 집어넣기
		String sql = "delete from productoption where opt_no=?";
		int result = 0;

		try {
			con = DbUtils.getConnection();
			con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(productOptionNumber));

			result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException(
						"에러: 상품옵션이 삭제 되지 않았습니다. / public int deleteProductOption(ProductOption productOption)");
			}
		} finally {
			con.commit();
			DbUtils.close(con, ps, null);
		}

		return result;
	}

}
