package controller;

import dto.ProductOption;
import service.ProductOptionService;
import view.EndView;
import view.FailView;
import view.SuccessView;

import java.util.List;

public class ProductOptionController {
    static ProductOptionService productOptionService = new ProductOptionService();

    public static void selectProductOptions() {
        try {
            List<ProductOption> productOptionList = productOptionService.selectProductOptions();
//            EndView.printProductOptionList(productOptionList); << 만들어야함
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void selectProductOptionByOptionNumber(int productOptionNumber) {
        try {
            ProductOption productOption = productOptionService.selectProductOptionByOptionNumber(productOptionNumber);
//            EndView.printProductOption(productOption); << 만들어야함
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void insertProductOption(ProductOption productOption) {
        try {
            productOptionService.insertProductOption(productOption);
            SuccessView.messagePrint("상품 옵션 입력이 정상적으로 진행 되었습니다.");
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void updateProductOption(ProductOption productOption) {
        try {
            productOptionService.updateProductOption(productOption);
            SuccessView.messagePrint("상품 옵션 업데이트가 정상적으로 진행 되었습니다.");
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void deleteProductOption(int productOptionNumber) {
        try {
            productOptionService.deleteProductOption(productOptionNumber);
            SuccessView.messagePrint("상품 옵션 삭제가 정상적으로 진행 되었습니다");
        }catch(Exception e){
            FailView.errorMessage(e.getMessage());
        }
    }
}


