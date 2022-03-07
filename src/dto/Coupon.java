package dto;

public class Coupon {
    private int couponNumber; // 쿠폰번호 매니저가 지정
    private String couponDetail; // 쿠폰 내용 (ex.10% 할인)
    private int couponDiscountRate; // 쿠폰 할인률 (ex.10)
    private int couponExpiration; // 쿠폰 만료기한 (ex.2022-04-01)

    public Coupon(int couponNumber, String couponDetail, int couponDiscountRate, int couponExpiration) {
        this.couponNumber = couponNumber;
        this.couponExpiration = couponExpiration;
        this.couponDetail = couponDetail;
        this.couponDiscountRate = couponDiscountRate;
    }

    public int getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(int couponNumber) {
        this.couponNumber = couponNumber;
    }

    public int getCouponExpiration() {
        return couponExpiration;
    }

    public void setCouponExpiration(int couponExpiration) {
        this.couponExpiration = couponExpiration;
    }

    public String getCouponDetail() {
        return couponDetail;
    }

    public void setCouponDetail(String couponDetail) {
        this.couponDetail = couponDetail;
    }

    public int getCouponDiscountRate() {
        return couponDiscountRate;
    }

    public void setCouponDiscountRate(int couponDiscountRate) {
        this.couponDiscountRate = couponDiscountRate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("couponNumber=").append(couponNumber);
        sb.append(", couponExpiration=").append(couponExpiration);
        sb.append(", couponDetail='").append(couponDetail).append('\'');
        sb.append(", couponDiscountRate=").append(couponDiscountRate);
        return sb.toString();
    }
}
