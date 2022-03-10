package dto;

public class OrderOption {
    private int orderOptionNo;
    private int orderProductNo;
    private int optionNumber;

    public OrderOption(int orderOptionNo, int orderProductNo, int optionNumber) {
        this.orderOptionNo = orderOptionNo;
        this.orderProductNo = orderProductNo;
        this.optionNumber = optionNumber;
    }

    /*
        추가한 이유:
            DB 에 insert 할 때 orderOptionNo 는 seq로 자동생성
                             orderProductNo 또한 OrderProduct 테이블에서 seq 로 자동생성
     */
    public OrderOption(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public int getOrderOptionNo() {
        return orderOptionNo;
    }

    public void setOrderOptionNo(int orderOptionNo) {
        this.orderOptionNo = orderOptionNo;
    }

    public int getOrderProductNo() {
        return orderProductNo;
    }

    public void setOrderProductNo(int orderProductNo) {
        this.orderProductNo = orderProductNo;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("orderOptionNo=").append(orderOptionNo);
        sb.append(", orderProductNo=").append(orderProductNo);
        sb.append(", optionNumber=").append(optionNumber);
        return sb.toString();
    }
}
