package dto;

import java.util.ArrayList;
import java.util.List;

public class Payment {
    private int paymentNumber;
    private String userId;
    private String paymentDate;
    private int paymentMethod;
    private int paymentPrice;
    private int userCouponNumber;
    
    List<OrderProduct> orderlist = new ArrayList<>();
    public Payment(String userId, int paymentMethod, int paymentPrice, int userCouponNumber) {
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.paymentPrice = paymentPrice;
        this.userCouponNumber = userCouponNumber;
    }

    
    public List<OrderProduct> getOrderlist() {
		return orderlist;
	}


	public void setOrderlist(List<OrderProduct> orderlist) {
		this.orderlist = orderlist;
	}


	public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(int paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public int getUserCouponNumber() {
        return userCouponNumber;
    }

    public void setUserCouponNumber(int userCouponNumber) {
        this.userCouponNumber = userCouponNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("paymentNumber=").append(paymentNumber);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", paymentDate='").append(paymentDate).append('\'');
        sb.append(", paymentMehtod=").append(paymentMethod);
        sb.append(", paymentPrice=").append(paymentPrice);
        sb.append(", userCouponNumber=").append(userCouponNumber);
        return sb.toString();
    }
}
