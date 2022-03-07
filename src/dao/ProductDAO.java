package dao;

import dto.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    /**
     * 전체상품
     *
     * @return List<Product>
     * @throws SQLException
     */
    List<Product> selectProducts() throws SQLException;

    /**
     * 상품번호로 상품검색
     *
     * @param productNumber
     * @return Product
     */
    Product selectProductByProductNumber(int productNumber);

    /**
     * 상품 추가
     *
     * @param product
     * @return int
     */
    int insertProduct(Product product);

    /**
     * 상품 수정
     *
     * @param product
     * @return int
     */
    int updateProduct(Product product);

    /**
     * 상품 삭제
     *
     * @param productNumber
     * @return int
     */
    int deleteProduct(int productNumber);

}
