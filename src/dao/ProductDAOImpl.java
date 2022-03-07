package dao;

import dto.Product;
import utils.SampleUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDAOImpl implements ProductDAO {
    private Properties proFile = SampleUtils.getProFile();

    @Override
    public List<Product> selectProducts() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // select 문일 때만 사용
        List<Product> list = new ArrayList<Product>(); // 리턴값
        String sql = proFile.getProperty("product.selectProducts"); // select * from product order by productNumber desc
        try {
            con = SampleUtils.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                // 열의 정보를 가져와서 Product에 담는다.
                // Product product = new Product(rs.getInt(1), rs.getByte(2), rs.getString(3),
                // rs.getInt(4), rs.getString(5));

                // Product를 list에 추가한다.
                // list.add(product);
            }
        } finally {
            SampleUtils.close(con, ps, rs);
        }
        return list;
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
