package view;

import java.util.List;

import dto.Coupon;
import dto.Product;
import dto.ProductOption;
import dto.UserCoupon;
import dto.UserMember;

public class EndView {
	public static void printProductList(List<Product> productList) {
		System.out.println("-----상품 " + productList.size() + "개 -------------");
		for (Product product : productList) {
			System.out.println(product);
		}
		System.out.println();
	}

	public static void selectByProductNoPrint(Product product) {
		System.out.println(product);
	}

	public static void printCouponlist(List<Coupon> couponlist) {
		System.out.println("---[ 총 쿠폰 개수: " + couponlist.size() + "개 ]----");
		for (Coupon coupon : couponlist) {
			System.out.println(coupon);
		}
		System.out.println();

	}

	public static void printCoupon(Coupon coupon) {
		System.out.println(coupon);
	}

	public static void printUserCouponlist(String userId, List<UserCoupon> usercouponlist) {
		System.out.println("--[ ID : " + userId + " ]님의 쿠폰목록 --");
		for (UserCoupon usercoupon : usercouponlist) {
			System.out.print(usercoupon.getCouponNumber() + " | ");
			for (Coupon coupon : usercoupon.getCouponlist()) {
				System.out.print(coupon.getCouponDetail() + " | " + coupon.getCouponExpiration() + " 만료 | ");
			}
			System.out.println(usercoupon.getCouponAmount() + "장 ");
		}

	}

	public static void printUserCoupon(UserCoupon usercoupon) {

	}

	public static void printProductOptionList(List<ProductOption> productOptionList) {
		System.out.println("-----상품 " + productOptionList.size() + "개 -------------");
		for (ProductOption productOption : productOptionList) {
			System.out.println(productOption);
		}
		System.out.println();
	}

	public static void printProductOption(ProductOption productOption) {
		System.out.println(productOption);
	}

	/**
	 * UserMember 출력
	 * 
	 * @param userMember
	 */
	public static void userMemberPrint(UserMember userMember) {
		System.out.println(userMember.toString());
	}
}
