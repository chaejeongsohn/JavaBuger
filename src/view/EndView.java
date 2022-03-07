package view;

import dto.Product;
import dto.ProductOption;

import java.util.List;

public class EndView {
    public static void printProductList(List<Product> productList) {
        System.out.println("-----상품 " + productList.size() + "개 -------------");
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println();
    }

    public static void printProductOptionList(List<ProductOption> productOptionList) {
        System.out.println("---------- 상품옵션 리스트 " + productOptionList.size() + "개 -------------");
        for(ProductOption productOption : productOptionList) {
            System.out.println(productOption);
        }
        System.out.println();
    }

    public static void printProductOption(ProductOption productOption) {
        System.out.println("---------- 선택 된 상품옵션 ----------");
        System.out.println(productOption);
        System.out.println();
    }
}
