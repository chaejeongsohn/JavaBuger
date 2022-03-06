package dao;

import dto.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public interface OrderProductDAO {
    /**
     * 전체 제품 구매내역 조회
     *
     * @return
     * @throws SQLException
     */
    List<OrderProduct> selectOrderProducts() throws SQLException;

    /**
     * 특정 OrderProduct 조회 (orderProductNo 이용)
     *
     * @param orderProductNo
     * @return
     * @throws SQLException
     */
    OrderProduct selectOrderProductByNumber(int orderProductNo) throws SQLException;

    /**
     * 제품 구매내역 추가
     *
     * @param orderProduct
     * @return
     * @throws SQLException
     */
    int insertOrderProduct(OrderProduct orderProduct) throws SQLException;

    /**
     * 해당 제품 구매내역 삭제 (orderProductNo 이용)
     *
     * @param orderProductNo
     * @return
     * @throws SQLException
     */
    int deleteOrderProduct(int orderProductNo) throws SQLException;

    /**
     * 제품 구매내역 수정
     *
     * @param orderProduct
     * @return
     * @throws SQLException
     */
    int updateOrderProduct(OrderProduct orderProduct) throws SQLException;

}
