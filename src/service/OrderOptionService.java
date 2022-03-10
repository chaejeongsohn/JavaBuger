package service;

import dao.OrderOptionDAO;
import dao.OrderOptionDAOImpl;
import dto.OrderOption;
import dto.OrderProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderOptionService {
    OrderOptionDAO orderOptionDAO = new OrderOptionDAOImpl();

    public List<OrderOption> selectOrderOptions() throws SQLException {
        return null;
    }

    public OrderOption selectOrderOptionByNumber(int orderOptionNo) throws SQLException {
        return null;
    }

    public boolean insertOrderOption(Connection con ,OrderProduct orderproduct) throws SQLException {
    	int result[] = orderOptionDAO.insertOrderOption(con, orderproduct);
    	for(int i :result) {
    		if(i !=1) {
    			throw new SQLException("[주문 실패] 주문등록에 실패했습니다.");
    		}
    	}return true;
    }

    public void deleteOrderOption(int orderOptionNo) throws SQLException {
    }

    public void updateOrderOption(OrderOption orderOption) throws SQLException {
    }
}
