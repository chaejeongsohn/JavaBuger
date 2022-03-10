package service;

import dto.CartProduct;
import dto.Product;
import dto.ProductOption;
import dto.UserMember;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    //DAO 끌어오기 위한 필드멤버
    private ProductService productService = new ProductService();
    private ProductOptionService productOptionService = new ProductOptionService();
    //메뉴 출력과 유저 입력을 받기 위한 리스트
    private List<Product> allProducts;
    private List<ProductOption> allProductOptions;
    //메뉴 장바구니 입력을 위한 임시 객체 및 객체리스트
    private List<ProductOption> productOptions = new ArrayList<>();
    private List<CartProduct> cartProducts = new ArrayList<>();
    //장바구니 객체 -> 후에 구매 파트에서 가져가 주문정보와 구매를 진행
    private Map<String, List<CartProduct>> userCart = new HashMap< String, List<CartProduct> >(); //String : User 객체의 userId
    private String userId;


    //init 작업
    public CartService() throws SQLException, NotFoundException {
        /*
            유저가 로그인을 했다면 -> userCart 에 유저 아이디를 입력
            유저가 로그인을 못 했다면 -> userCart 에 null 입력

            allProducts 와 allProductOptions 채워넣기 (용도1: 화면 출력 / 용도2: user input 받는 용도)
        */
        UserMember session = UserSessionService.getUserSession();
        if(session != null) {
            userId = session.getUserId();
        }
        userCart.put(userId, cartProducts);
        allProducts = productService.selectProducts();
        allProductOptions = productOptionService.selectProductOptions();
    }



    // 상품옵션번호만 받아도 됌.
    public boolean addOption(int productOptionNumber) throws SQLException {

        return addOptionToOptions(productOptionNumber);
    }

    /*
        methods to fill up temp lists
     */
    public void addOptionsToCartProduct(CartProduct cartProduct) {
        cartProduct.setOptionList(this.productOptions);
    }

    public void addCartProductToCartProducts(CartProduct cartProduct) {
        this.cartProducts.add(cartProduct);
    }

    public void addCartProductsToUserCart() {
        this.userCart.put(userId, cartProducts);
    }


    public boolean increaseUserCartQuantity(int index){
        List<CartProduct> cartProductList = userCart.get(userId);
        CartProduct cartItem = cartProductList.get(index);
        if(cartItem == null) return false;

        cartItem.setQuantity( cartItem.getQuantity() + 1 );

        return true;
    }

    public boolean decreaseUserCartQuantity(int index){
        List<CartProduct> cartProductList = userCart.get(userId);
        CartProduct cartItem = cartProductList.get(index);
        if(cartItem == null) return false;

        int quantity = cartItem.getQuantity();
        if(quantity > 0) {
            cartItem.setQuantity( quantity - 1 );
        }

        return true;
    }

    public Map<String,List<CartProduct>> getUserCart() {
        return this.userCart;
    }

    public boolean clearUserCart() {
        boolean result = false;
        List<CartProduct> removed = userCart.remove(userId);
        if(removed != null) {
            result = true;
        }

        return result;
    }
 
    


    ///////////////methods for display (to use in CartController)///////////////
    // 위에서 이미 가져온 세션의 userId 입력하여 카트내역만 전달한다.
    public List<CartProduct> getAllCartProductsForDisplay() {
        return userCart.get(userId);
    }

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
        return this.allProductOptions;
    }


    ////////////// private method for userCart //////////////
    private boolean addProductToCartProduct(int productNumber, CartProduct cartProduct) throws SQLException, NotFoundException {
        Product product = productService.selectProductByProductNumber(productNumber);
        cartProduct.setProduct(product);

        return this.cartProducts.add(cartProduct);
    }

    private boolean addOptionToOptions(int productOptionNumber) throws SQLException {
        ProductOption productOption = productOptionService.selectProductOptionByOptionNumber(productOptionNumber);

        return this.productOptions.add(productOption);
    }


}

