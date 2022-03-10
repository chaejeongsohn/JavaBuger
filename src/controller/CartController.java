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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartController {

    private static CartService cartService;

    static {
        try {
            cartService = new CartService();
        } catch (SQLException | NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void handleProductOrder(String category) {
        CartProduct cartProduct = null; // 공유 카트아이템 객체

        List<Product> productsByCategory = cartService.getAllProductsForDisplay(category);
        EndView.printProductsByCategory(productsByCategory);

        //user's input needed here as int, the productNumber of choice
        int productNumber = CartMenuView.askUserInput("\n원하시는 상품의 번호를 입력하세요 >> ");

//        try {
//            cartProduct = cartService.addProduct(productNumber);
//        } catch (SQLException | NotFoundException e) {
//            FailView.errorMessage(e.getMessage());
//        }
        cartProduct = new CartProduct();
        for (Product product : productsByCategory) {
            if (product.getProductNumber() == productNumber) {
                cartProduct.setProduct(product);
                cartProduct.setQuantity(1);
                break;
            }
        }
        if (cartProduct.getProduct() == null) {
            FailView.errorMessage("선택하신 상품을 장바구니에 담지 못하였습니다.");
            return;
        }

        //add product options to "Burger" Product
        if (category.equals("B")) {
            List<ProductOption> productOptions = cartService.getAllProductOptionsForDisplay();
            List<ProductOption> productoptions2  = new ArrayList<>();
            for(ProductOption option : productOptions) {
            	if(option.getCategoryNumber().equals("B")) {
            		productoptions2.add(option);
            	}
            }
            EndView.printAllProductOptions(productoptions2);

            while (true) {
                int optionNumber = CartMenuView.askUserInput("\n원하시는 옵션의 번호를 입력하세요 (더 필요 없을 시 0 입력) >> "); // 원하는 옵션을 선택
                if (optionNumber == 0) break; // 0 -> no more option

//                    cartService.addOption(optionNumber);
                boolean found = false;
                for (ProductOption productOption : productOptions) {
                    if (productOption.getOptionNumber() == optionNumber) {
                        cartProduct.getOptionList().add(productOption);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    FailView.errorMessage("not found option");
                }
            }
        } // end of product options
        
        if (category.equals("C")) {
            List<ProductOption> productOptions = cartService.getAllProductOptionsForDisplay();
            List<ProductOption> productoptions2  = new ArrayList<>();
            for(ProductOption option : productOptions) {
            	if(option.getCategoryNumber().equals("C")) {
            		productoptions2.add(option);
            	}
            }
        
            EndView.printAllProductOptions(productoptions2);

            while (true) {
                int optionNumber = CartMenuView.askUserInput("\n원하시는 옵션의 번호를 입력하세요 (더 필요 없을 시 0 입력) >> "); // 원하는 옵션을 선택
                if (optionNumber == 0) break; // 0 -> no more option

//                    cartService.addOption(optionNumber);
                boolean found = false;
                for (ProductOption productOption : productOptions) {
                    if (productOption.getOptionNumber() == optionNumber) {
                        cartProduct.getOptionList().add(productOption);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    FailView.errorMessage("not found option");
                }
            }
        }
        // 마지막에만 장바구니 아이템을 담는다
        cartService.addCartProductToCartProducts(cartProduct);
        //3.
        SuccessView.messagePrint("선택하신 상품을 장바구니에 담았습니다.\n");
    }

    // 주문을 userCart 로 체크아웃
    public static void saveOrderInfo() {
        cartService.addCartProductsToUserCart();
    }

    public static void getAllCartProductsForDisplay() {
        List<CartProduct> cartProducts = cartService.getAllCartProductsForDisplay();
        EndView.printCartProducts(cartProducts);
    }

    public static void increaseUserCartQuantity() {
        int index = CartMenuView.askUserInput("원하시는 장바구니 내역의 번호를 입력해주세요 >> ");
        boolean result = cartService.increaseUserCartQuantity(index - 1);
        if (result) {
            SuccessView.messagePrint("장바구니 업데이트가 정상적으로 진행 되었습니다.");
        } else {
            FailView.errorMessage("장바구니 업데이트가 정상적으로 진행되지 않았습니다.");
        }
    }

    public static void decreaseUserCartQuantity() {
        int index = CartMenuView.askUserInput("원하시는 장바구니 내역의 번호를 입력해주세요 >> ");
        boolean result = cartService.decreaseUserCartQuantity(index - 1);
        if (result) {
            SuccessView.messagePrint("장바구니 업데이트가 정상적으로 진행 되었습니다.");
        } else {
            FailView.errorMessage("장바구니 업데이트가 정상적으로 진행되지 않았습니다.");
        }
    }

    public static void clearUserCart() {
        boolean result = cartService.clearUserCart();
        if (result) {
            SuccessView.messagePrint("장바구니 비우기가 정상적으로 진행 되었습니다.");
        } else {
            FailView.errorMessage("장바구니 비우기가 정상적으로 진행되지 않았습니다.");
        }
    }

    // 구매 파트에서 처음에 호출해주어야 하는 메소드 (자바 자료구조에만 담긴 유저의 오더정보를 가져온다)
    public static Map<String, List<CartProduct>> getUserCart() {
        return cartService.getUserCart();
    }


}


