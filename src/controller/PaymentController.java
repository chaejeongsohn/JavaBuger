package controller;

import java.sql.SQLException;

import dto.Payment;
import exception.NotFoundException;
import service.PaymentService;
import view.FailView;
import view.SuccessView;


public class PaymentController {
    static PaymentService paymentService = new PaymentService();

    public static void selectPayments() {
    }

    public static void selectPaymentByPayNo(int PaymentNumber) {

    }

    public static void selectPaymentByUserId(String userId) {
    }

    public static void selectPaymentByPaymentDate(String paymentDate) {
    }

    public static void insertPayment(Payment payment) {
    	try{
    		paymentService.insertPayment(payment);
    		SuccessView.messagePrint("[주문 완료!]"+payment.getPaymentMehtod()+"로 결제완료되었습니다.");
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }

    public static void deletePayment(int paymentNumber) {
    }

    public static void updatePayment(Payment payment) {
    }

}
