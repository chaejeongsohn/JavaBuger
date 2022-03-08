package controller;

import dto.Product;
import service.ProductService;
import view.EndView;
import view.FailView;
import view.SuccessView;

import java.util.List;

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

	public static Product selectProductByProductNumber(int productNumber) {
		Product product = null;
		try {
			product = productService.selectProductByProductNumber(productNumber);
			EndView.selectByProductNoPrint(product);

		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}

		return product;
	}

	public static void insertProduct(Product product) {
		try {
			productService.insertProduct(product);
			SuccessView.messagePrint("등록되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void updateProduct(Product product) {
		try {
			productService.updateProduct(product);
			SuccessView.messagePrint("수정되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void deleteProduct(int productNumber) {
		try {
			productService.deleteProduct(productNumber);
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
