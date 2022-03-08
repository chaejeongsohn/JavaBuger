package dto;

public class SalesDate {
	private String date;
	private int totalsales;
	
	public SalesDate(String date, int totalsales) {
		this.date =date;
		this.totalsales = totalsales;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getTotalsales() {
		return totalsales;
	}

	public void setTotalsales(int totalsales) {
		this.totalsales = totalsales;
	}
	
	@Override
	public String toString() {
		return date +" = "+ totalsales;
	}
}
