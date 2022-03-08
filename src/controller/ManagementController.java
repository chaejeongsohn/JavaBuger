package controller;

import service.ManagementService;
import view.FailView;
import view.SuccessView;

public class ManagementController {
	static ManagementService managementService = new ManagementService();

	public static void updatePassword(String managerPw) {
		try {
			managementService.updatePassword(managerPw);
			SuccessView.messagePrint("비밀번호가 수정되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void updateStoreName(String storeName) {
		try {
			managementService.updateStoreName(storeName);
			SuccessView.messagePrint("가게이름이 수정되었습니다.");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	public static void checkPassword(String managerPw) {

		if (managerPw.equals(managerPw)) {
			System.out.println("비밀번호가 일치합니다");
		}else {
			System.out.println("비밀번호가 불일치합니다 다시 입력해주세요");
		}
	}
}
