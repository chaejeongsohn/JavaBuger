package service;

import dao.OrderProductDAO;
import dao.OrderProductDAOImpl;
import dto.OrderProduct;
import dto.Payment;

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

    public boolean insertOrderProduct(Connection con , Payment payment) throws SQLException {
    	int result [] = orderProductDAO.insertOrderProduct(con, payment);
    	for(int i :result) {
    		if(i !=1) {
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
