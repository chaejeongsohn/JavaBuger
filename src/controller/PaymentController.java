package controller;



import dto.Payment;
import dto.Ranking;



import dto.SalesDate;

import dto.UserPaymentDetailByDate;
import dto.UserTotalPaymentDetail;
import service.PaymentService;
import view.EndView;
import view.FailView;
import view.SuccessView;

import java.sql.SQLException;
import java.util.ArrayList;
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
    	try{
    		paymentService.selectSalseByDate();
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
    	}catch(NullPointerException ex) {
    		FailView.errorMessage("NullPointerException이 발생했습니다.");
    	}

    }
    
    public static int insertPayment(Payment payment) throws SQLException {
    	try{
    		int result = paymentService.insertPayment(payment);
    		SuccessView.messagePrint("[주문 완료!]"+payment.getPaymentMethod()+"로 결제완료되었습니다.");
            return result;
    	}catch(SQLException e) {
    		FailView.errorMessage(e.getMessage());
            throw e;
    	}
    }

    public static void selectPaymentByUserId(String userId) {
        try {
            List<UserTotalPaymentDetail> userTotalPaymentDetailList = paymentService.selectPaymentByUserId(userId);
            for(UserTotalPaymentDetail userTotalPaymentDetail : userTotalPaymentDetailList){
                System.out.println(userTotalPaymentDetail);
            }
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }


    public static void selectUserPaymentByPaymentDate(String userId, String paymentDate){
        try{
            List<UserPaymentDetailByDate> userPaymentDetailByDateList = paymentService.selectUserPaymentByPaymentDate(userId, paymentDate);
            List<UserPaymentDetailByDate> paymentList = new ArrayList<>();

            for(UserPaymentDetailByDate userPaymentDetailByDate : userPaymentDetailByDateList){
                UserPaymentDetailByDate payment = new UserPaymentDetailByDate(0,null, null);
                int lastPayNumber = 0;
                int lastOrderProductNumber = 0;
                List<String> optionList = null;
                if(userPaymentDetailByDate.getPayNumber() != lastPayNumber){
                    payment.setPayPrice(userPaymentDetailByDate.getPayPrice());
                    payment.setProductName(userPaymentDetailByDate.getProductName());
                    lastPayNumber = userPaymentDetailByDate.getPayNumber();
                    if(userPaymentDetailByDate.getOrderProductNumber() != lastOrderProductNumber){
                        optionList.add(userPaymentDetailByDate.getProductOptionName());
                        lastOrderProductNumber = userPaymentDetailByDate.getOrderProductNumber();
                    }
                }
                payment.setProductOptionNameList(optionList);
                System.out.println(userPaymentDetailByDate);
            }
        }catch(SQLException e){
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
