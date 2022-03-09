package controller;


import dto.Payment;
import dto.Ranking;
import dto.SalesDate;
import dto.UserPaymentDetail;
import service.PaymentService;
import view.EndView;
import view.FailView;
import view.SuccessView;

import java.sql.SQLException;
import java.util.List;


public class PaymentController {
    static PaymentService paymentService = new PaymentService();

    /*카테고리 총 매출순위*/
    public static void selectSalesrankingAll() {
        selectSalesranking("A");
        selectSalesranking("B");
        selectSalesranking("C");
        selectSalesranking("D");
    }

    /*메뉴별 매출순위*/
    public static void selectSalesranking(String category) {
        try {
            List<Ranking> ranklist = paymentService.selectSalesranking(category);
            EndView.printSalesRanking(ranklist);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    /*일별 매출순위*/
    public static void selectSalseByDate() {
        try {
            List<SalesDate> saleslist = paymentService.selectSalseByDate();
            EndView.printDateSales(saleslist);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }


    public static void insertPayment(Payment payment) {
        try {
            paymentService.insertPayment(payment);
            SuccessView.messagePrint("[주문 완료!]" + payment.getPaymentMehtod() + "로 결제완료되었습니다.");
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void selectPaymentByUserId(String userId) {
        try {
            List<UserPaymentDetail> userPaymentDetailList= paymentService.selectPaymentByUserId(userId);
            for(UserPaymentDetail userPaymentDetail : userPaymentDetailList){
                System.out.println(userPaymentDetail);
            }
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }


    public static void selectPayments() {

    }


    public static void selectPaymentByPayNo(int PaymentNumber) {

    }


    public static void deletePayment(int paymentNumber) {
    }

    public static void updatePayment(Payment payment) {
    }

}
