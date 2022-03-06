package dao;

import dto.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public List<Payment> selectPayments() throws SQLException {
        return null;
    }

    @Override
    public Payment selectPaymentByPayNo(int PaymentNumber) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> selectPaymentByUserId(String userId) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> selectPaymentByPaymentDate(String paymentDate) throws SQLException {
        return null;
    }

    @Override
    public int insertPayment(Payment payment) throws SQLException {
        return 0;
    }

    @Override
    public int deletePayment(int paymentNumber) throws SQLException {
        return 0;
    }

    @Override
    public int updatePayment(Payment payment) throws SQLException {
        return 0;
    }
}
