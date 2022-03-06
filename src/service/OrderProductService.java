package service;

import dao.OrderProductDAO;
import dao.OrderProductDAOImpl;
import dto.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public class OrderProductService {
    OrderProductDAO orderProductDAO = new OrderProductDAOImpl();

    public List<OrderProduct> selectOrderProducts() throws SQLException {
        return null;
    }

    public OrderProduct selectOrderProductByNumber(int orderProductNo) throws SQLException {
        return null;
    }

    public void insertOrderProduct(OrderProduct orderProduct) throws SQLException {
    }

    public void deleteOrderProduct(int orderProductNo) throws SQLException {
    }

    public void updateOrderProduct(OrderProduct orderProduct) throws SQLException {
    }
}
