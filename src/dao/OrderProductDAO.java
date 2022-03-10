package dao;

import dto.OrderProduct;
import dto.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderProductDAO {

    /**
     * 제품 구매내역 추가
     *
     * @param orderProductList
     * @return
     * @throws SQLException
     */
    int[] insertOrderProduct(Connection con ,List<OrderProduct> orderProductList) throws SQLException;

}
