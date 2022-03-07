package dto;

public class ProductCategory {
    private char categoryNumber; // ex) A~D
    private String categoryName; // ex) set, burger, side, drink

    public ProductCategory(char categoryNumber, String categoryName) {
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
    }

    public char getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(char categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("categoryNumber=").append(categoryNumber);
        sb.append(", categoryName='").append(categoryName).append('\'');
        return sb.toString();
    }
}
