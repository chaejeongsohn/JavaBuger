package dto;

import java.util.List;

public class CartProduct {
    private Product product;
    private List<ProductOption> optionList;
    private int quantity;


    public CartProduct(Product product, List<ProductOption> optionList, int quantity) {
        this.product = product;
        this.optionList = optionList;
        this.quantity = quantity;
    }

    public CartProduct() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<ProductOption> optionList) {
        this.optionList = optionList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("product=").append(product);
        sb.append(", optionList=").append(optionList);
        sb.append(", quantity=").append(quantity);
        return sb.toString();
    }
}
