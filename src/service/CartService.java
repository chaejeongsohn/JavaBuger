package service;

import dto.CartProduct;
import dto.UserMember;

import java.util.List;
import java.util.Map;

public class CartService {
    private ProductService productService = new ProductService();
    private Map<String, List<CartProduct>> userCart; //String : User 객체의 userId
    /* 일단 보류
    private List<Product> allProducts
    private List<ProductOption> allProductOptions
     */

    public CartService(){}

    /////////////////////////////////////////////
    ///////////////public methods///////////////
    /*

     */
    public boolean insertUserSession(String userId){
        //toDo

        return false;
    }

    /*

     */
    public void handleProductOrder(){
        //toDo
    }

    /*

     */
    public boolean increaseUserCartQuantity(int quantity){
        //toDo

        return false;
    }

    /*

     */
    public boolean decreaseUserCartQuantity(int quantity){
        //toDo

        return false;
    }

    /*

     */
    public Map<String,List<CartProduct>> getUserCart() {
        //toDo

        return null;
    }

    /*

     */
    public boolean clearUserCart() {
        //toDo

        return false;
    }

    /////////////////////////////////////////////
    ///////////////private methods///////////////
    private void viewCartProducts() {
        //toDo
    }

    private void insertProductToUserCart() {
        //toDo
    }

    private void insertOptionToUserCart() {
        //toDO
    }

    /* 일단 보류
    private List<Product> getAllProducts(){}
    private List<ProductOption> getAllProductOptions(){}
    private List<Product> getProductsByCategory(String category){}
    private List<ProductOption> getProductOptionsByCategory(String category){}
     */




}

