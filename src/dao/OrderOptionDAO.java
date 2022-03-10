package dao;

import dto.OrderOption;
import dto.OrderProduct;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderOptionDAO {


    /**
     * 옵션 구매내역 추가
     *
     * @param orderOption
     * @return
     * @throws SQLException
     */
	int[] insertOrderOption(Connection con, List<OrderOption> orderOptionList) throws SQLException;

}
