package controller;

import dto.CartProduct;
import exception.NotFoundException;
import service.CartService;
import view.EndView;
import view.FailView;
import view.SuccessView;

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
        } catch (SQLException | NotFoundException e) {
            e.printStackTrace();
            FailView.errorMessage("장바구니 기능을 구현하는 도중 DAO 에서 에러 발생 했습니다.");
        }
    }

    /*

     */
    public static void handleProductOrder(char category){
        boolean result = cartService.handleProductOrder(category);
        if(result) {
            SuccessView.messagePrint("선택하신 상품을 장바구니에 담았습니다.");
        }else {
            FailView.errorMessage("선택하신 상품을 장바구니에 담지 못하였습니다.");
        }
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


