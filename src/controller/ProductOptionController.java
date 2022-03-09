package controller;

import java.util.List;

import dto.ProductOption;
import service.ProductOptionService;
import view.EndView;
import view.FailView;
import view.SuccessView;

public class ProductOptionController {
	static ProductOptionService productOptionService = new ProductOptionService();

	public static void selectProductOptions() {
		try {
			List<ProductOption> productOptionList = productOptionService.selectProductOptions();
			EndView.printProductOptionList(productOptionList);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static ProductOption selectProductOptionByOptionNumber(int productOptionNumber) {
		ProductOption productOption = null;
		try {
			productOption = productOptionService.selectProductOptionByOptionNumber(productOptionNumber);
			EndView.printProductOption(productOption);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return productOption;
	}

	public static void insertProductOption(ProductOption productOption) {
		try {
			productOptionService.insertProductOption(productOption);
			SuccessView.messagePrint("상품 옵션 입력이 정상적으로 진행 되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void updateProductOption(ProductOption productOption) {
		try {
			productOptionService.updateProductOption(productOption);
			SuccessView.messagePrint("상품 옵션 업데이트가 정상적으로 진행 되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void deleteProductOption(int productOptionNumber) {
		try {
			productOptionService.deleteProductOption(productOptionNumber);
			SuccessView.messagePrint("상품 옵션 삭제가 정상적으로 진행 되었습니다");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
