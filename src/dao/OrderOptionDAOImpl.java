package dao;

import dto.OrderOption;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderOptionDAOImpl implements OrderOptionDAO {
    ProductOptionDAOImpl productoptionDAO = new ProductOptionDAOImpl();

    @Override
    public List<OrderOption> selectOrderOptions() throws SQLException {
        return null;
    }

    @Override
    public OrderOption selectOrderOptionByNumber(int orderOptionNo) throws SQLException {
        return null;
    }

    @Override
    public int insertOrderOption(Connection con, OrderOption orderOption) throws SQLException {
        PreparedStatement ps = null;
        String sql = "insert into orderoption values (ORDER_OPT_NO_SEQ.nextval, ?,?)";
        int result = 0;

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, orderOption.getOrderProductNo());
            ps.setInt(2, orderOption.getOptionNumber());
            result = ps.executeUpdate();
        } finally {
            DbUtils.close(null, ps);
        }
        return result;
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
