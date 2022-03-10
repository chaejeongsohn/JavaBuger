package dao;

import dto.OrderOption;
import dto.OrderProduct;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class OrderOptionDAOImpl implements OrderOptionDAO {
    ProductOptionDAOImpl productoptionDAO = new ProductOptionDAOImpl();
    private Properties proFile = DbUtils.getProFile();

    @Override
    public int[] insertOrderOption(Connection con, OrderProduct orderproduct) throws SQLException {
    	PreparedStatement ps= null;
    	String sql = proFile.getProperty("orderoption.insert");
    	//insert into orderoption values (ORDER_OPT_NO_SEQ.nextval, ORDER_PRD_NO_SEQ.currval,?)
        int result []=null;
        
        try {
        	ps = con.prepareStatement(sql);
        	for(OrderOption orderoption : orderproduct.getOrderoptionlist()) {
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
}
