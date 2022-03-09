package controller;

import java.sql.SQLException;

import dto.Payment;
import exception.NotFoundException;
import service.PaymentService;
import view.FailView;
import view.SuccessView;


public class PaymentController {
    static PaymentService paymentService = new PaymentService();
<<<<<<< Updated upstream
=======
    
    /*카테고리 총 매출순위*/
    public static void selectSalesrankingAll() {
    	selectSalesranking("A");
    	selectSalesranking("B");
    	selectSalesranking("C");
    	selectSalesranking("D");
    }
    
    /*메뉴별 매출순위*/
    public static void selectSalesranking(String category) {
    	try{
    		List<Ranking> ranklist =  paymentService.selectSalesranking(category);
    		EndView.printSalesRanking(ranklist);
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    /*일별 매출순위*/
    public static void selectSalseByDate() {
    	try{
    		List<SalesDate> saleslist =paymentService.selectSalseByDate();
    		EndView.printDateSales(saleslist);
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    
    public static void insertPayment(Payment payment) {
    	try{
    		paymentService.insertPayment(payment);
    		SuccessView.messagePrint("[주문 완료!]"+payment.getPaymentMethod()+"로 결제완료되었습니다.");
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }
    /**
     * 해당 사용자 월별 구매 조회
     * @param userId
     * @param month
     */
    public static void selectUserPaymentByMonth(String userId, int month){
        try{
            paymentService.selectUserPaymentByMonth(userId, month);
            SuccessView.messagePrint("");
        }catch(SQLException e){
            FailView.errorMessage(e.getMessage());
        }
    }
>>>>>>> Stashed changes

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
