package controller;

import dto.CartProduct;
import service.CartService;
import view.FailView;
import view.SuccessView;

import java.util.List;
import java.util.Map;

public class CartController {
    /*

     */
    private static CartService cartService = new CartService();

    CartController(){};

    /*

     */
    public static void insertUserSession(String userId){
        //toDo
        boolean res = cartService.insertUserSession(userId);
        if(res == false) {
            FailView.errorMessage("실패: userCart 에 유저아이디가 들어가지 않았습니다. 해당 메소드: cartService.insertUserSession(String userId)");
        }

    }

    /*

     */
    public static void handleProductOrder(){
        //toDo
    }

    /*

     */
    public static void increaseUserCartQuantity(int quantity){
        //toDo

    }

    /*

     */
    public static void decreaseUserCartQuantity(int quantity){
        //toDo
    }

    /*

     */
    public static Map<String, List<CartProduct>> getUserCart() {
        //toDo

        return null;
    }

    /*

     */
    public static boolean clearUserCart() {
        //toDo

        return false;
    }


}


