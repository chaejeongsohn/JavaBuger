package dto;

import java.util.List;

public class UserPaymentDetailByDate {
    private int payPrice;
    private int payNumber;
    private String productName;
    private int orderProductNumber;
    private String productOptionName;
    private List<String> productOptionNameList;

    public UserPaymentDetailByDate(int payPrice, int payNumber, String productName, int orderProductNumber, String productOptionName) {
        this.payPrice = payPrice;
        this.payNumber = payNumber;
        this.productName = productName;
        this.orderProductNumber = orderProductNumber;
        this.productOptionName = productOptionName;
    }

    public UserPaymentDetailByDate(int payPrice, String productName, List<String> productOptionNameList) {
        this.payPrice = payPrice;
        this.productName = productName;
        this.productOptionNameList = productOptionNameList;
    }

    public List<String> getProductOptionNameList() {
        return productOptionNameList;
    }

    public void setProductOptionNameList(List<String> productOptionNameList) {
        this.productOptionNameList = productOptionNameList;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public int getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(int payNumber) {
        this.payNumber = payNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getOrderProductNumber() {
        return orderProductNumber;
    }

    public void setOrderProductNumber(int orderProductNumber) {
        this.orderProductNumber = orderProductNumber;
    }

    public String getProductOptionName() {
        return productOptionName;
    }

    public void setProductOptionName(String productOptionName) {
        this.productOptionName = productOptionName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("총 가격 =").append(payPrice);
        sb.append(" 주문한 제품명 : ").append(productName).append('\'');
        sb.append(" - 추가한 옵션 : ").append(productOptionNameList);
        return sb.toString();
    }
}
