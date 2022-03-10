package service;

import dao.OrderProductDAO;
import dao.OrderProductDAOImpl;
import dto.OrderProduct;

import java.sql.Connection;
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

    public boolean insertOrderProduct(Connection con, List<OrderProduct> orderProductList) throws SQLException {
        int result[] = orderProductDAO.insertOrderProduct(con, orderProductList);
        for (int i : result) {
            if (i != 1) {
                throw new SQLException("[주문 실패] 주문등록에 실패했습니다.");
            }
        }
        return true;
    }

    public void deleteOrderProduct(int orderProductNo) throws SQLException {
    }

    public void updateOrderProduct(OrderProduct orderProduct) throws SQLException {
    }
}
