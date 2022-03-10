package service;


import dao.PaymentDAO;
import dao.PaymentDAOImpl;
import dto.*;
import view.EndView;

import java.sql.SQLException;
import java.util.List;


public class PaymentService {
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    public List<Ranking> selectSalesranking(String category) throws SQLException {
        List<Ranking> ranklist = paymentDAO.selectSalesranking(category);
        Ranking rank = ranklist.get(0);
        if (ranklist == null || ranklist.size() == 0)
            throw new SQLException("카테고리[ " + rank.getCategoryName() + " ]에 해당하는 매출 기록이 없습니다.");
        return ranklist;
    }

    public List<SalesDate> selectSalseByDate() throws SQLException, NullPointerException {
        List<SalesDate> salesList = paymentDAO.selectSalseByDate();
        EndView.printDateSales(salesList);
        if (salesList == null || salesList.size() == 0) throw new SQLException("저장된 매출내역이 없습니다.");
        return salesList;
    }

    public int insertPayment(Payment payment) throws SQLException {
        int result = paymentDAO.insertPayment(payment);
        if (result == 0) throw new SQLException("[주문 실패] 주문하지 못 했습니다.");
        return result;
    }

    public List<UserTotalPaymentDetail> selectPaymentByUserId(String userId) throws SQLException {
        List<UserTotalPaymentDetail> userTotalPaymentDetailList = paymentDAO.selectPaymentByUserId(userId);
        if (userTotalPaymentDetailList.size() == 0) throw new SQLException("해당 주문 내역이 없습니다.");
        return userTotalPaymentDetailList;
    }


    public void selectUserPaymentByPaymentDate(String userId, String paymentDate) throws SQLException {
        List<UserPaymentDetail> userPaymentDetailByDateList = paymentDAO.selectUserPaymentByPaymentDate(userId, paymentDate);
        if (userPaymentDetailByDateList.size() == 0) throw new SQLException("해당 주문내역 없습니다.");
        String date = paymentDate.substring(0, 4) + "-" + paymentDate.substring(4, 6) + "-" + paymentDate.substring(6, 8);
        System.out.println("--- " + userId + " 님의 " + date + " 의 구매내역 ---");
        int lastPayNumber = 0;
        int lastOrderProductNumber = 0;
        for (UserPaymentDetail userPaymentDetailByDate : userPaymentDetailByDateList) {
            UserPaymentDetail payment = new UserPaymentDetail(0, null, null);


            if (userPaymentDetailByDate.getPayNumber() != lastPayNumber) {
                System.out.println();
                System.out.print("###");
                System.out.println(" 총가격 : " + userPaymentDetailByDate.getPayPrice());
                System.out.println(" 상품이름 : " + userPaymentDetailByDate.getProductName());
                lastPayNumber = userPaymentDetailByDate.getPayNumber();
                lastOrderProductNumber = userPaymentDetailByDate.getOrderProductNumber();
                if (userPaymentDetailByDate.getOrderProductNumber() == lastOrderProductNumber) {
                    if (userPaymentDetailByDate.getProductOptionName() != null) {
                        System.out.println("           ㄴ> 옵션 : " + userPaymentDetailByDate.getProductOptionName());
                    }
                }
            } else {
                if (userPaymentDetailByDate.getOrderProductNumber() != lastOrderProductNumber) {
                    System.out.println("          " + userPaymentDetailByDate.getProductName());
                    lastOrderProductNumber = userPaymentDetailByDate.getOrderProductNumber();
                }
                if (userPaymentDetailByDate.getProductOptionName() != null) {
                    System.out.println("           ㄴ> 옵션 : " + userPaymentDetailByDate.getProductOptionName());
                }
            }
        }
    }


    public void selectUserPayments(String userId) throws SQLException {
        List<UserPaymentDetail> userPaymentDetailList = paymentDAO.selectUserPayments(userId);
        if (userPaymentDetailList.size() == 0) throw new SQLException("해당 주문내역 없습니다.");
        System.out.println("--- " + userId + " 님의 구매내역 ---");
        int lastPayNumber = 0;
        int lastOrderProductNumber = 0;
        for (UserPaymentDetail userPaymentDetail : userPaymentDetailList) {
            UserPaymentDetail payment = new UserPaymentDetail(0, null, null);


            if (userPaymentDetail.getPayNumber() != lastPayNumber) {
                System.out.println();
                System.out.print("###");
                System.out.println(" 구매날짜 : " + userPaymentDetail.getPaymentDate());
                System.out.println(" 총가격 : " + userPaymentDetail.getPayPrice());
                System.out.println(" 상품이름 : " + userPaymentDetail.getProductName());
                lastPayNumber = userPaymentDetail.getPayNumber();
                lastOrderProductNumber = userPaymentDetail.getOrderProductNumber();
                if (userPaymentDetail.getOrderProductNumber() == lastOrderProductNumber) {
                    if (userPaymentDetail.getProductOptionName() != null) {
                        System.out.println("           ㄴ> 옵션 : " + userPaymentDetail.getProductOptionName());
                    }
                }
            } else {
                if (userPaymentDetail.getOrderProductNumber() != lastOrderProductNumber) {
                    System.out.println("          " + userPaymentDetail.getProductName());
                    lastOrderProductNumber = userPaymentDetail.getOrderProductNumber();
                }
                if (userPaymentDetail.getProductOptionName() != null) {
                    System.out.println("           ㄴ> 옵션 : " + userPaymentDetail.getProductOptionName());
                }
            }
        }
    }

    public String selectUserPaymentLastOrderDate(String userId) throws SQLException {
        String lastDate = paymentDAO.selectUserPaymentLastOrderDate(userId);
        if (lastDate == null) throw new SQLException("해당 주문내역 없습니다.");
        return lastDate;
    }

}
