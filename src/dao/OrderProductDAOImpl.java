package dao;

import dto.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public class OrderProductDAOImpl implements OrderProductDAO {

    @Override
    public List<OrderProduct> selectOrderProducts() throws SQLException {
        return null;
    }

    @Override
    public OrderProduct selectOrderProductByNumber(int orderProductNo) throws SQLException {
        return null;
    }

    @Override
    public int insertOrderProduct(OrderProduct orderProduct) throws SQLException {
        return 0;
    }

    @Override
    public int deleteOrderProduct(int orderProductNo) throws SQLException {
        return 0;
    }

    @Override
    public int updateOrderProduct(OrderProduct orderProduct) throws SQLException {
        return 0;
    }
}
