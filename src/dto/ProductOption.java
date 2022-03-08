package dto;

public class ProductOption {
    private int optionNumber;
    private String categoryNumber;
    private String optionName;
    private int optionPrice;

    public ProductOption(String optionName) {
        this.optionName = optionName;
    }

    public ProductOption(int optionNumber, String categoryNumber, String optionName, int optionPrice) {
        this.optionNumber = optionNumber;
        this.categoryNumber = categoryNumber;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }

    public ProductOption(String optionName, int optionPrice) {
        this.optionName = optionName;
        this.optionPrice = optionPrice;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(int optionPrice) {
        this.optionPrice = optionPrice;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("optionNumber=").append(optionNumber);
        sb.append(", categoryNumber=").append(categoryNumber);
        sb.append(", optionName='").append(optionName).append('\'');
        sb.append(", optionPrice=").append(optionPrice);
        return sb.toString();
    }
}
