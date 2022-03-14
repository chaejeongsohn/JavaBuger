package controller;


import dto.Payment;
import dto.Ranking;
import dto.UserTotalPaymentDetail;
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


    /*일별 매출 내역*/
    public static void selectSalseByDate() {
        try {
            paymentService.selectSalseByDate();
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        } catch (NullPointerException ex) {
            FailView.errorMessage("NullPointerException이 발생했습니다.");
        }

    }

    public static int insertPayment(Payment payment)  {
    	 int result =0;
        try {
        	result =  paymentService.insertPayment(payment);
            //SuccessView.messagePrint("[주문 완료!]" + payment.getPaymentMethod() + "로 결제완료되었습니다.");
            
        } catch (SQLException e) {
        	e.printStackTrace();
            FailView.errorMessage(e.getMessage());
        }
        return result;
    }

    public static void selectPaymentByUserId(String userId) {
        try {
            List<UserTotalPaymentDetail> userTotalPaymentDetailList = paymentService.selectPaymentByUserId(userId);
            for (UserTotalPaymentDetail userTotalPaymentDetail : userTotalPaymentDetailList) {
                System.out.println(userTotalPaymentDetail);
            }
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }


    public static void selectUserPaymentByPaymentDate(String userId, String paymentDate) {
        try {
            paymentService.selectUserPaymentByPaymentDate(userId, paymentDate);

        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void selectUserPayments(String userId) {
        try {
            paymentService.selectUserPayments(userId);

        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static String selectUserPaymentLastOrderDate(String userId) {
        String lastDate = null;
        try {
            lastDate = paymentService.selectUserPaymentLastOrderDate(userId);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
        String dateOfYear = lastDate.substring(0, 4);
        String dateOfMonth = lastDate.substring(5, 7) ;
        String dateOfDay = lastDate.substring(8,10);
        String date = dateOfYear + dateOfMonth+ dateOfDay;
        return date;
    }


}
