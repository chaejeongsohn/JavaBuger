package view;

import dto.*;

import java.util.List;

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
            for (Coupon coupon : usercoupon.getCouponList()) {
                System.out.print(coupon.getCouponDetail() + " | " + coupon.getCouponExpiration() + " 만료 | ");
            }
            System.out.println(usercoupon.getCouponAmount() + "장 ");
        }
    }

    public static void printUserCoupon(UserCoupon usercoupon) {
        System.out.print("--쿠폰번호 [ " + usercoupon.getCouponNumber() + " ] | ");
        List<Coupon> couponlist = usercoupon.getCouponList();
        Coupon coupon = couponlist.get(0);
        System.out.println(coupon.getCouponDetail() + " | " + coupon.getCouponExpiration() + " 만료 | 를 선택하셨습니다.");
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
     *
     * @param userMember
     */
    public static void userMemberPrint(UserMember userMember) {
        System.out.println(userMember.toString());
    }

    //////////////////////// CartController ////////////////////////
    public static void printProductsByCategory(List<Product> productsByCategory) {
        System.out.println("------------ 선택하신 상품 카테고리 리스트 ------------");
        for (Product p : productsByCategory) {
        	System.out.println("----------------------------");
            System.out.println("| 상품번호 | 상품이름 | 상품가격 |");
            System.out.println("---------------------- ----");
            System.out.println(" "+p.getProductNumber() + "\t" + p.getProductName() + "\t" + p.getProductPrice());

            System.out.print("상품정보: ");
            System.out.println(p.getProductDetail());
            System.out.println();
            
        }
    }

    public static void printAllProductOptions(List<ProductOption> productOptions) {
        System.out.println("------------ 상품에 추가 할 상품옵션 리스트 ------------");
        for (ProductOption po : productOptions) {
        	System.out.println("--------------------------------");
            System.out.println("| 상품옵션번호 | 상품옵션이름 | 상품가격 |");
            System.out.println("--------------------------------");
            System.out.println("  "+po.getOptionNumber() + "  \t  " + po.getOptionName() + "      " + po.getOptionPrice() + "\n");
            System.out.println();
        }
    }

    public static void printProductOption(ProductOption productOption) {
        System.out.println(productOption);
    }

    public static void printSalesRanking(List<Ranking> ranking) {
        int rankno = 1;
        Ranking rank = ranking.get(0);
        System.out.println("---[ " + rank.getCategoryName() + " ] 매출 순위 ---");
        for (Ranking ranklist : ranking) {
            System.out.println(rankno + ". [ " + ranklist.getProductName() + " ] 총 매출액: " + ranklist.getTotalPrice() + "원");
            rankno = rankno + 1;
        }
        System.out.println();

    }

    public static void printDateSales(List<SalesDate> saleslist) {
        System.out.println("---[ 일별 매출 현황 ]---");
        for (SalesDate sales : saleslist) {
            System.out.println(sales.getDate() + " | " + sales.getTotalsales() + "원");
        }
        System.out.println();
    }

	public static void printCartProducts(List<CartProduct> cartProducts) {
		int count;
		if(cartProducts == null) {
			System.out.println("---------- 장바구니가 비어있습니다. ----------");
			return;
		}else {
			count = cartProducts.size();
		}

		System.out.println("---------- 장바구니 내역: 총 " + count + " 개 ------------");
		for (int i = 0; i < count; i++) {
			System.out.print((i + 1) + ". ");

			CartProduct cp = cartProducts.get(i);
			System.out.print("상품명 : " + cp.getProduct().getProductName() + ", ");
			System.out.print("상품옵션 리스트 : ");
			if(cp.getOptionList() != null) {
				for (ProductOption po : cp.getOptionList()) {
					System.out.print(po.getOptionName() + " ");
				}
			}
			System.out.print(", 장바구니 상품 현재수량 :" + cp.getQuantity());
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printAllUserMmber(List<UserMember> userlist) {
		System.out.println("-------회원 목록-------");
		for(UserMember user : userlist) {
			System.out.println("회원아이디: "+user.getUserId()+" | 회원 이름: "+user.getUserName()+" | 회원 전화번호: "+user.getUserPhone()+ " | 회원 생년월일: "+user.getUserBirthDay()+" | 회원 가입일: "+user.getUserJoinDate());
		}
		
	}

}
