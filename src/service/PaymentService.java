package service;


import dao.PaymentDAO;
import dao.PaymentDAOImpl;
import dto.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    public List<Payment> selectPayments() {
        return null;
    }

    public Payment selectPaymentByPayNo(int PaymentNumber) {return  null;}
    public List<Payment> selectPaymentByUserId(String userId) {
        return null;
    }

    public List<Payment> selectPaymentByPaymentDate(String paymentDate) {
        return null;
    }

    public void insertPayment(Payment payment) throws SQLException {
    	int result = paymentDAO.insertPayment(payment);
    	if(result==0) throw new SQLException("[주문 실패] 주문하지 못 했습니다.");
    }

    public void deletePayment(int paymentNumber) {
    }

    public void updatePayment(Payment payment) {
    }

}
