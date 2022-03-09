package dto;

public class UserTotalPaymentDetail {
    private String payDate;
    private int pay_price;
    private String productName;
    private int orderProductAmount;

    public UserTotalPaymentDetail(String payDate, int pay_price, String productName, int orderProductAmount) {
        this.payDate = payDate;
        this.pay_price = pay_price;
        this.productName = productName;
        this.orderProductAmount = orderProductAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getPay_price() {
        return pay_price;
    }

    public void setPay_price(int pay_price) {
        this.pay_price = pay_price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOrderProductAmount() {
        return orderProductAmount;
    }

    public void setOrderProductAmount(int orderProductAmount) {
        this.orderProductAmount = orderProductAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("구매일자 : ").append(payDate.substring(0,10)).append(" ");
        sb.append("구매금액 : ").append(pay_price).append(" ");
        sb.append("구매상품이름 및 수량 : ").append(productName).append(" ");
        sb.append(orderProductAmount).append("개");
        return sb.toString();
    }
}
