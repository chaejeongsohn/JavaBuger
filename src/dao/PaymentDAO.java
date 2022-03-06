package dao;

import dto.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {
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


    /**
     * 아이디로 구매내역들 조회
     *
     * @return
     * @throws SQLException
     */
    List<Payment> selectPaymentByUserId(String userId) throws SQLException;

    /**
     * 날짜로 구매내역들 조회
     *
     * @return
     * @throws SQLException
     */
    List<Payment> selectPaymentByPaymentDate(String paymentDate) throws SQLException;

    /**
     * 구매내역 추가
     *
     * @param payment
     * @return
     * @throws SQLException
     */
    int insertPayment(Payment payment) throws SQLException;

    /**
     * 구매내역 삭제
     *
     * @param paymentNumber
     * @return
     * @throws SQLException
     */
    int deletePayment(int paymentNumber) throws SQLException;


    /**
     * 구매내역 수정
     *
     * @param payment
     * @return
     * @throws SQLException
     */
    int updatePayment(Payment payment) throws SQLException;
}
