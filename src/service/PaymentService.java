package service;

import dao.PaymentDAO;
import dao.PaymentDAOImpl;
import dto.Payment;

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

    public void insertPayment(Payment payment)  {
    }

    public void deletePayment(int paymentNumber) {
    }

    public void updatePayment(Payment payment) {
    }

}
