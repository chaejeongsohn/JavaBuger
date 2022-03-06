package dao;

import dto.OrderOption;

import java.sql.SQLException;
import java.util.List;

public interface OrderOptionDAO {

    /**
     * 전체 옵션 구매내역 조회
     *
     * @return
     * @throws SQLException
     */
    List<OrderOption> selectOrderOptions() throws SQLException;

    /**
     * 특정 OrderOption 조회 (orderOptionNo 이용)
     *
     * @param orderOptionNo
     * @return
     * @throws SQLException
     */
    OrderOption selectOrderOptionByNumber(int orderOptionNo) throws SQLException;

    /**
     * 옵션 구매내역 추가
     *
     * @param orderOption
     * @return
     * @throws SQLException
     */
    int insertOrderOption(OrderOption orderOption) throws SQLException;


    /**
     * 해당 옵션 구매내역 삭제 (orderOptionNo 이용)
     *
     * @param orderOptionNo
     * @return
     * @throws SQLException
     */
    int deleteOrderOption(int orderOptionNo) throws SQLException;

    /**
     * 옵션 구매내역 수정
     *
     * @param orderOption
     * @return
     * @throws SQLException
     */
    int updateOrderOption(OrderOption orderOption) throws SQLException;
}
