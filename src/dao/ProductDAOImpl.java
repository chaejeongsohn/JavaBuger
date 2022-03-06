package dao;

import dto.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> selectProducts() throws SQLException {
        return null;
    }

    @Override
    public Product selectProductByProductNumber(int productNumber) {
        return null;
    }

    @Override
    public int insertProduct(Product product) {
        return 0;
    }

    @Override
    public int updateProduct(Product product) {
        return 0;
    }

    @Override
    public int deleteProduct(int productNumber) {
        return 0;
    }
}
