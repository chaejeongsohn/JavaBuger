package service;

import controller.ProductController;
import dto.CartProduct;
import dto.Product;
import dto.ProductOption;
import dto.UserMember;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CartService {
    private ProductService productService = new ProductService();
    private ProductOptionService productOptionService = new ProductOptionService();

    /*
    Map<String, List<CartProduct>> userCart 가 어떻게 생겼는지.. (test data) :
        로그인 유저인 경우:
            Map<String, List<CartProduct>> userCart = new HashMap<>();
            userCart.put('test123', null);
            userCart.put('test123', new ArrayList() {{ add(**CartProduct 객체); add(**CartProduct 객체) ... }});
            CartProduct cp1 = new CartProduct( new Product(DTO 보고 내용채우기) , new ArrayList() {{ add(**ProductOption 객체); add(**ProductOption 객체); ... }}
            CartProduct cp2 = ~
            CartProduct cp3 = ~
            ...

        비회원 유저인 경우:
            위에 'test123' 대신 null 입력
     */
    private Map<String, List<CartProduct>> userCart; //String : User 객체의 userId
    private List<Product> allProducts;
    private List<ProductOption> allProductOptions;
    private String userId;

    //init 작업

    public CartService() throws SQLException, NotFoundException {
        /*
            유저가 로그인을 했다면 -> userCart 에 유저 아이디를 입력
            유저가 로그인을 못 했다면 -> userCart 에 null 입력

            allProducts 와 allProductOptions 채워넣기 (용도1: 화면 출력 / 용도2: userCart 맵에 담기)
         */
        userId = UserSessionService.getUserSession().getUserId();
        userCart.put(userId, null);
        allProducts = productService.selectProducts();
        allProductOptions = productOptionService.selectProductOptions();
    }


    ///////////////public methods///////////////
    /*

     */
    public boolean handleProductOrder(char category){
        //toDo

        return false;
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
        return userCart;
    }

    /*

     */
    public boolean clearUserCart() {
        //toDo

        return false;
    }


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

