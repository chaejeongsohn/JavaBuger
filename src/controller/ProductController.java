package controller;

import dto.Product;
import service.ProductService;
import view.EndView;
import view.FailView;

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

    public static void selectProductByProductNumber(int productNumber) {

    }

    public static void insertProduct(Product product) {

    }

    public static void updateProduct(Product product) {

    }

    public static void deleteProduct(Product product) {

    }

    public static void addSoldOutProduct(Product product) {

    }

    public static void deleteSoldOutProduct(Product product) {

    }

    public static void showSoldOutProductList() {

    }
}
