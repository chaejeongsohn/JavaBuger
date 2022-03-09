package dto;

public class Management {
	private int managerPw; // 프로그램 관리 비밀번호
	private String storeName; // 가게이름

	public Management(int managerPw) {
		this.managerPw = managerPw;
	}

	public Management(String storeName) {
		this.storeName = storeName;
	}

	public int getManagerPw() {
		return managerPw;
	}

	public void setManagerPw(int managerPw) {
		this.managerPw = managerPw;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("");
		sb.append("storeName='").append(storeName).append('\'');
		return sb.toString();
	}
}
