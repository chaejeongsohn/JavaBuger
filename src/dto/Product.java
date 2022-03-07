package dto;

public class Product {
    private int productNumber;
    private String categoryNumber;
    private String productName;
    private int productPrice;
    private String productDetail;

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(int productNumber, String categoryNumber, String productName, int productPrice, String productDetail) {
        this.productNumber = productNumber;
        this.categoryNumber = categoryNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetail = productDetail;
    }

    public Product(String productName, int productPrice, String productDetail) {
        this.categoryNumber = categoryNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetail = productDetail;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("productNumber=").append(productNumber);
        sb.append(", categoryNumber=").append(categoryNumber);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productDetail='").append(productDetail).append('\'');
        return sb.toString();
    }
}
