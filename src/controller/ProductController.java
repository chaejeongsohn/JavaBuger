package controller;

import java.util.List;

import dto.Product;
import service.ProductService;
import view.EndView;
import view.FailView;
import view.SuccessView;

public class ProductController {
	static ProductService productService = new ProductService();

	public static void selectProducts() {
		try {
			List<Product> productList = productService.selectProducts();
			EndView.printProductList(productList);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * productNumber로 상품검색
	 */
	public static void selectProductByProductNumber(int productNumber) {
		try {
			Product product = productService.selectProductByProductNumber(productNumber);
			EndView.selectByProductNoPrint(product);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

	}

	/**
	 * product 등록
	 */
	public static void insertProduct(Product product) {
		try {
			productService.insertProduct(product);
			SuccessView.messagePrint("등록되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * product 수정
	 */
	public static void updateProduct(Product product) {
		try {
			productService.insertProduct(product);
			SuccessView.messagePrint("수정되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * product 삭제
	 */
	public static void deleteProduct(Product product) {
		try {
			productService.insertProduct(product);
			SuccessView.messagePrint("삭제되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void addSoldOutProduct(Product product) {

	}

	public static void deleteSoldOutProduct(Product product) {

	}

	public static void showSoldOutProductList() {

	}
}
