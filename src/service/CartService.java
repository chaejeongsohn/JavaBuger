package service;

import dto.CartProduct;
import dto.Product;
import dto.ProductOption;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private List<Product> allProducts;
    private List<ProductOption> allProductOptions;
    private List<CartProduct> cartProducts = new ArrayList<>();
    private Map<String, List<CartProduct>> userCart = new HashMap< String, List<CartProduct> >(); //String : User 객체의 userId
    private String userId;

    //init 작업
    public CartService() throws SQLException, NotFoundException {
        /*
            유저가 로그인을 했다면 -> userCart 에 유저 아이디를 입력
            유저가 로그인을 못 했다면 -> userCart 에 null 입력

            allProducts 와 allProductOptions 채워넣기 (용도1: 화면 출력 / 용도2: userCart 맵에 담기)
        */
        userId = UserSessionService.getUserSession().getUserId();
        userCart.put(userId, cartProducts);
        allProducts = productService.selectProducts();
        allProductOptions = productOptionService.selectProductOptions();
    }


    ///////////////public methods///////////////
    /*
        상품번호를 받아 해당 상품번호에 맞는 상품을 userCart 에 집어 넣는다
        (단, cartController 만들 것)
     */
    public boolean handleProductOrder(int productNumber){
        System.out.println("productNumber : " + productNumber);
        System.out.println("cart");
        //insertProductToCartProduct(productNumber);


        return false;
    }

    /*
        상품옵션을 받을 때는 상품번호 와 상품옵션번호 를 둘 다 꼭 받아야한다.
     */
    public boolean handleProductOptionOrder(int productNumber, int productOptionNumber) {
        System.out.println("productNumber : " + productNumber);
        System.out.println("productOptionNumber" + productOptionNumber);

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

    ///////////////public methods for display (to use in CartController)///////////////
    /*
    filter by category
     */
    public List<Product> getAllProductsForDisplay(String category){
        List<Product> productsByCategory = new ArrayList<>();
        for(Product p : allProducts) {
            if(p.getCategoryNumber().equals(category)) {
                productsByCategory.add(p);
            }
        }

        return productsByCategory;
    }

    public List<ProductOption> getAllProductOptionsForDisplay(){
        return allProductOptions;
    }

    public List<CartProduct> getAllCartProductsForDisplay() {
        return userCart.get(userId); //위에서 이미 가져온 세션의 userId 입력하여 카트내역만 전달한다.
    }

    ////////////// private method for userCart //////////////
    private void insertProductToCartProduct(int productNumber, CartProduct cartProduct) throws SQLException, NotFoundException {
        Product product = productService.selectProductByProductNumber(productNumber);
        cartProduct.setProduct(product);
        cartProducts.add(cartProduct);
    }

    private void insertOptionToCartProduct() {
        //toDO

    }


    ///////////////private methods///////////////


}

