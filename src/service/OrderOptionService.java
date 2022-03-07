package service;

import dao.OrderOptionDAO;
import dao.OrderOptionDAOImpl;
import dto.OrderOption;

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

    public void insertOrderOption(OrderOption orderOption) throws SQLException {
    }

    public void deleteOrderOption(int orderOptionNo) throws SQLException {
    }

    public void updateOrderOption(OrderOption orderOption) throws SQLException {
    }
}
