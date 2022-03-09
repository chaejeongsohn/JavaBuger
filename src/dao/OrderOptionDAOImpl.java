package dao;

import dto.OrderOption;
import dto.OrderProduct;
import dto.ProductOption;
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
    public int[] insertOrderOption(Connection con, OrderProduct orderproduct) throws SQLException {
    	PreparedStatement ps= null;
    	String sql = "insert into orderoption values (ORDER_OPT_NO_SEQ.nextval, order_prd_no.currval,?)";
        int result []=null;
        
        try {
        	
        	ps = con.prepareStatement(sql);
        	for(OrderOption orderoption : orderproduct.getOrderoptionlist()) {
        		//ProductOption option =productoptionDAO.selectProductOptionByOptionNumber((orderoption.getOptionNumber()));
        		
        		ps.setInt(1, orderoption.getOptionNumber());
        		ps.addBatch();
        		ps.clearParameters();
        	}
        	result =ps.executeBatch();
        }finally {
        	DbUtils.close(null, ps);
        }
    	return result ;
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
