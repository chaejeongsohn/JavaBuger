package dao;

import dto.OrderOption;

import java.sql.SQLException;
import java.util.List;

public class OrderOptionDAOImpl implements OrderOptionDAO {
    @Override
    public List<OrderOption> selectOrderOptions() throws SQLException {
        return null;
    }

    @Override
    public OrderOption selectOrderOptionByNumber(int orderOptionNo) throws SQLException {
        return null;
    }

    @Override
    public int insertOrderOption(OrderOption orderOption) throws SQLException {
        return 0;
    }

    @Override
    public int deleteOrderOption(int orderOptionNo) throws SQLException {
        return 0;
    }

    @Override
    public int updateOrderOption(OrderOption orderOption) throws SQLException {
        return 0;
    }
}
