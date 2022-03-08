package controller;

import dto.CartProduct;
import dto.Product;
import dto.ProductOption;
import exception.NotFoundException;
import service.CartService;
import view.EndView;
import view.FailView;
import view.SuccessView;
import view.menu.CartMenuView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CartController {
    /*

     */
    private static CartService cartService;

    static {
        try {
            cartService = new CartService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

//    static {
//        try {
//            cartService = new CartService();
//        } catch (SQLException | NotFoundException e) {
//            e.printStackTrace();
//            FailView.errorMessage("장바구니 기능을 구현하는 도중 DAO 에서 에러 발생 했습니다.");
//        }
//    }

    /*
        1. print method for the given category -> as parameter of view methods
        2. get user's choice and deliver it as the parameter of handleProductOrder
        3. create an if condition for Burger Category "B"
           -> if B, then ask for Production Option as well.
     */
    public static void handleProductOrder(String category){
        //1.
        List<Product> productsByCategory = cartService.getAllProductsForDisplay(category);
        EndView.printProductsByCategory(productsByCategory);

        //user's input needed here as int, the productNumber of choice
        int productNumber = CartMenuView.askUserInput();

        //2.
        boolean result = cartService.handleProductOrder(productNumber);
        if(result) {
            SuccessView.messagePrint("선택하신 상품을 장바구니에 담았습니다.");
        }else {
            FailView.errorMessage("선택하신 상품을 장바구니에 담지 못하였습니다.");
        }

        //3.버거메뉴는 추가적으로 상품옵션(토핑) 까지 받아야 한다.
        if(category.equals("B") || category.equals("b")) {
            while(true) {
                List<ProductOption> productOptions = cartService.getAllProductOptionsForDisplay();
                EndView.printAllProductOptions(productOptions);

                int productOptionNumber = CartMenuView.askUserInput(); // 원하는 상품("옵션")을 선택해주세요
                cartService.handleProductOptionOrder(productNumber, productOptionNumber);
            }
        } // end of if

    }

    /*

     */
    public static void increaseUserCartQuantity(int quantity){
        boolean result = cartService.increaseUserCartQuantity(quantity);
        if(result) {
            SuccessView.messagePrint("선택하신 장바구니 상품 업데이트가 정상적으로 진행 되었습니다.");
        }else {
            FailView.errorMessage("선택하신 장바구니 상품 업데이트가 정상적으로 진행되지 않았습니다.");
        }
    }

    /*

     */
    public static void decreaseUserCartQuantity(int quantity){
        boolean result = cartService.decreaseUserCartQuantity(quantity);
        if(result) {
            SuccessView.messagePrint("선택하신 장바구니 상품 업데이트가 정상적으로 진행 되었습니다.");
        }else {
            FailView.errorMessage("선택하신 장바구니 상품 업데이트가 정상적으로 진행되지 않았습니다.");
        }
    }

    /*
        구매 파트에서 처음에 호출해주어야 하는 메소드 (자바 자료구조에만 담긴 유저의 오더정보를 가져온다)
     */
    public static Map<String, List<CartProduct>> getUserCart() {
        return cartService.getUserCart();
    }

    /*

     */
    public static void clearUserCart() {
        boolean result = cartService.clearUserCart();
        if(result) {
            SuccessView.messagePrint("장바구니 비우기가 정상적으로 진행 되었습니다.");
        }else {
            FailView.errorMessage("장바구니 비우기가 정상적으로 진행되지 않았습니다.");
        }
    }


}


