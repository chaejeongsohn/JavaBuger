package dao;


import dto.*;

import java.sql.SQLException;
import java.util.List;


public interface PaymentDAO {

    /*메뉴별 매출순위*/
    List<Ranking> selectSalesranking(String category) throws SQLException;


    /*일별 매출순위*/
    List<SalesDate> selectSalseByDate() throws SQLException;


    /*사용자 구매내역 추가*/
    int insertPayment(Payment payment) throws SQLException;


    /**
     * 전체 구매내역 조회
     *
     * @return
     * @throws SQLException
     */
    List<Payment> selectPayments() throws SQLException;


    /**
     * 해당 구매내역 조회 (paymentNumber 이용)
     *
     * @return
     * @throws SQLException
     */
    Payment selectPaymentByPayNo(int PaymentNumber) throws SQLException;

    public List<UserPaymentDetailByDate> selectUserPaymentByPaymentDate(String userId, String paymentDate) throws SQLException;


    /**
     * 아이디로 구매내역 조회
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    List<UserTotalPaymentDetail> selectPaymentByUserId(String userId) throws SQLException;

}
