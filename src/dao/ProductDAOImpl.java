package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.Product;
import utils.DbUtils;

public class ProductDAOImpl implements ProductDAO {
	private Properties proFile = DbUtils.getProFile();

	@Override
	public List<Product> selectProducts() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // select 문일 때만 사용
		List<Product> list = new ArrayList<Product>(); // 리턴값
		String sql = proFile.getProperty("product.selectProducts"); // select * from product order by PRD_NO desc
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				// 열의 정보를 가져와서 Product에 담는다.
				Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5));

				// Product를 list에 추가한다.
				list.add(product);
			}
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return list;
	}

	@Override
	public Product selectProductByProductNumber(int productNumber) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		String sql = proFile.getProperty("product.selectProductByProductNumber"); // select * from product where
																					// productNumber = ?
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, productNumber); // ?값
			rs = ps.executeQuery();
			if (rs.next()) {// 한번만 나가니까 while이 아니라 if 써야함
				// 열의 정보를 가져와서 Product에 담는다.
				product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));

			}
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return product;
	}

	@Override
	public int insertProduct(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("product.insert"); //
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			// ?의 개수만큼 순서대로 setXxxx설정 필요
			ps.setString(1, product.getCategoryNumber());
			ps.setString(2, product.getProductName());
			ps.setInt(3, product.getProductPrice());
			ps.setString(4, product.getProductDetail());

			result = ps.executeUpdate();

		} finally {
			DbUtils.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int updateProduct(Product product) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("product.update"); // update product set CATEGORY_NO = ?, PRD_NAME = ?,
															// PRD_PRICE = ?,PRD_DETAIL = ? where PRD_NO = ?
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			// ?의 개수만큼 순서대로 setXxxx설정 필요
			ps.setString(1, product.getCategoryNumber());
			ps.setString(2, product.getProductName());
			ps.setInt(3, product.getProductPrice());
			ps.setString(4, product.getProductDetail());
			ps.setInt(5, product.getProductNumber());

			result = ps.executeUpdate();

		} finally {
			DbUtils.close(con, ps, null);
		}
		return result;
	}

	@Override
	public int deleteProduct(int productNumber) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("product.delete"); // delete from product where PRD_NO = ?
		try {
			con = DbUtils.getConnection();
			ps = con.prepareStatement(sql);
			// ?의 개수만큼 순서대로 setXxxx설정 필요
			ps.setInt(1, productNumber);

			result = ps.executeUpdate();

		} finally {
			DbUtils.close(con, ps, null);
		}
		return result;
	}
}
