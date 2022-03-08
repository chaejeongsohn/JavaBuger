package dto;

import java.util.ArrayList;
import java.util.List;

public class OrderProduct {
    private int orderProductNo;
    private int paymentNumber;
    private int productNumber;
    private int orderProductAmount;
    
    List<OrderOption> orderoptionlist = new ArrayList<OrderOption>();

    public OrderProduct(int orderProductNo, int paymentNumber, int productNumber, int orderProductAmount) {
        this.orderProductNo = orderProductNo;
        this.paymentNumber = paymentNumber;
        this.productNumber = productNumber;
        this.orderProductAmount = orderProductAmount;
    }

    public int getOrderProductNo() {
        return orderProductNo;
    }

    public void setOrderProductNo(int orderProductNo) {
        this.orderProductNo = orderProductNo;
    }

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getOrderProductAmount() {
        return orderProductAmount;
    }

    public void setOrderProductAmount(int orderProductAmount) {
        this.orderProductAmount = orderProductAmount;
    }
    
    

    public List<OrderOption> getOrderoptionlist() {
		return orderoptionlist;
	}

	public void setOrderoptionlist(List<OrderOption> orderoptionlist) {
		this.orderoptionlist = orderoptionlist;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("orderProductNo=").append(orderProductNo);
        sb.append(", paymentNumber=").append(paymentNumber);
        sb.append(", productNumber=").append(productNumber);
        sb.append(", orderProductAmount=").append(orderProductAmount);
        return sb.toString();
    }
}
