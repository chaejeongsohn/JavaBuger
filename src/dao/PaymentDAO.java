package dao;


import dto.Payment;
import dto.Ranking;
import dto.SalesDate;
import dto.UserPaymentDetail;


import java.sql.SQLException;
import java.util.List;



public interface PaymentDAO {
	
	 /*메뉴별 매출순위*/
	List<Ranking> selectSalesranking(String category)throws SQLException;
	
	
	/*일별 매출순위*/
	List<SalesDate> selectSalseByDate()throws SQLException, NullPointerException;
	
	
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


    /**
     * 아이디로 구매내역 조회
     * @param userId
     * @return
     * @throws SQLException
     */
    List<UserPaymentDetail> selectPaymentByUserId(String userId) throws SQLException;

    /**
     * 날짜로 구매내역들 조회
     *
     * @return
     * @throws SQLException
     */


    /**
     * 구매내역 추가
     *
     * @param payment
     * @return
     * @throws SQLException
     */


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
