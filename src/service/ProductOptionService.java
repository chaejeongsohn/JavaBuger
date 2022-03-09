package service;

import dao.ProductOptionDAOImpl;
import dto.ProductOption;

import java.sql.SQLException;
import java.util.List;

public class ProductOptionService {
	ProductOptionDAOImpl productOptionDAO = new ProductOptionDAOImpl();

	public List<ProductOption> selectProductOptions() throws SQLException {
		List<ProductOption> productOptionList = productOptionDAO.selectProductOptions();

		return productOptionList;
	}

	public ProductOption selectProductOptionByOptionNumber(int productOptionNumber) throws SQLException {
		ProductOption productOption = productOptionDAO.selectProductOptionByOptionNumber(productOptionNumber);

		return productOption;
	}

	public void insertProductOption(ProductOption productOption) throws SQLException {
		int result = productOptionDAO.insertProductOption(productOption);
		if (result == 0)
			throw new SQLException("에러: 상품옵션 입력 에러 / insertProductOption(ProductOption productOption)");
	}

	public void updateProductOption(ProductOption productOption) throws SQLException {
		int result = productOptionDAO.updateProductOption(productOption);
		if (result == 0)
			throw new SQLException("에러: 상품옵션 업데이트 에러 / updateProductOption(ProductOption productOption)");
	}

	public void deleteProductOption(int productOptionNumber) throws SQLException {
		int result = productOptionDAO.deleteProductOption(productOptionNumber);
		if (result == 0)
			throw new SQLException("에러: 상품옵션 삭제 에러 / deleteProductOption(int productOptionNumber)");
	}
}
