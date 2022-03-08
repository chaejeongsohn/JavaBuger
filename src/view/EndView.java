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
		System.out.println("--[ ID : "+userId+" ]님의 쿠폰목록 --");
		for(UserCoupon usercoupon : usercouponlist) {
			System.out.print(usercoupon.getCouponNumber()+" | ");
			for(Coupon coupon : usercoupon.getCouponList()) {
				System.out.print(coupon.getCouponDetail()+" | "+coupon.getCouponExpiration()+" 만료 | ");
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

    /**
     * UserMember 출력
     * @param userMember
     */
    public static void userMemberPrint(UserMember userMember) {
        System.out.println(userMember.toString());
    }


	//////////////////////// CartController ////////////////////////
	public static void printProductsByCategory(List<Product> productsByCategory) {
		System.out.println("------------ 선택하신 상품 카테고리 리스트 ------------");
		for(Product p : productsByCategory) {
			System.out.println("상품번호\t상품이름\t상품가격");
			System.out.println("----------------------------------------------------");
			System.out.println( p.getProductNumber() + "\t" + p.getProductName() + "\t" + p.getProductPrice() );

			System.out.print("상품정보: ");
			System.out.println( p.getProductDetail() );
		}
	}

	public static void printAllProductOptions(List<ProductOption> productOptions) {
		System.out.println("------------ 상품에 추가 할 상품옵션 리스트 ------------");
		for (ProductOption po : productOptions) {
			System.out.println("상품옵션번호\t상품옵션이름\t상품가격");
			System.out.println("----------------------------------------------------");
			System.out.println(po.getOptionNumber() + "\t" + po.getOptionName() + "\t" + po.getOptionPrice() + "\n");
		}
	}

	public static void printProductOption(ProductOption productOption) {
		System.out.println(productOption);
	}

}
