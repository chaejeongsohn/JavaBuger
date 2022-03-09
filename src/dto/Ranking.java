package dto;

public class Ranking {
	private String productName;
	private int totalPrice;
	private String categoryName;
	
	public Ranking() {}
	
	public Ranking(String productName, int totalPrice, String categoryName) {
		this.productName = productName;
		this.totalPrice =totalPrice;
		this.categoryName = categoryName;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		
		return " | "+productName+" | "+totalPrice+" | "+ categoryName;
				
	}

}
