package service;


import dao.PaymentDAO;
import dao.PaymentDAOImpl;


import dto.Payment;
import dto.Ranking;
import dto.SalesDate;
import dto.UserPaymentDetailByDate;
import dto.UserTotalPaymentDetail;

import view.EndView;



import java.sql.SQLException;
import java.util.List;


public class PaymentService {
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    
    public List<Ranking> selectSalesranking(String category) throws SQLException{
    	List<Ranking> ranklist= paymentDAO.selectSalesranking(category);
    	Ranking rank = ranklist.get(0);
    	if(ranklist==null||ranklist.size()==0)throw new SQLException("카테고리[ "+rank.getCategoryName()+" ]에 해당하는 매출 기록이 없습니다.");
    	return ranklist;
	}

    public List<SalesDate> selectSalseByDate() throws SQLException, NullPointerException{
    	List<SalesDate> saleslist = paymentDAO.selectSalseByDate();
    	EndView.printDateSales(saleslist);
    	if(saleslist==null||saleslist.size()==0)throw new SQLException("저장된 매출내역이 없습니다.");



        return null;
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

    public List<UserPaymentDetailByDate> selectUserPaymentByPaymentDate(String userId, String paymentDate) throws SQLException {
        List<UserPaymentDetailByDate> userPaymentDetailByDateList = paymentDAO.selectUserPaymentByPaymentDate(userId, paymentDate);
        if (userPaymentDetailByDateList.size() == 0) throw new SQLException("해당 주문내역 없습니다.");
        return userPaymentDetailByDateList;
    }


    public static void selectPayments() {

    }

    public void deletePayment(int paymentNumber) {

    }

    public void updatePayment(Payment payment) {

    }


}
