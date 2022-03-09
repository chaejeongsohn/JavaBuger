package view.menu;

import java.util.Scanner;

import controller.ManagementController;
import controller.ProductController;
import controller.ProductOptionController;
import dto.Product;
import dto.ProductOption;

public class ManagerMenuView {
	private static Scanner scanner = new Scanner(System.in);

	public static void managerMenu() {
		System.out.println("비밀번호를 입력해주세요.");
		while (true) {
			String pwd = scanner.nextLine();
			boolean check = ManagementController.checkPassword(pwd);
			while (check) {
				printManagerMenu();
				System.out.println("메뉴를 선택해주세요 > ");
				int menu = Integer.parseInt(scanner.nextLine());
				switch (menu) {
				case 1: // 상품관리
					productControl();
					break;
				case 2: // 옵션관리
					optionControl();
					break;
				case 3: // 매출관리
					break;
				case 4: // 매점관리
					storeControl();
					break;
				case 5: // 뒤로 돌아가기
					return;
				case 6: // 프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
			}
			System.out.println("다시 입력해주세요.");
		}
	}// managerMenu 메소드

	private static void printManagerMenu() {
		System.out.println("---------------------");
		System.out.println("1. 상품관리  2. 옵션관리  3. 매출관리  4. 매점관리  5. 이전으로 돌아가기  6. 프로그램 종료");
	}

	private static void productControl() {
		System.out.println("원하시는 상품관리를 선택해주세요 >");
		System.out.println("1. 상품수정  2. 상품등록  3. 상품삭제  4. 품절등록  5. 이전으로 돌아가기  6. 프로그램 종료");
		while (true) {
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 상품수정
				printUpdateProductMenu();
				printManagerMenu();
				break;
			case 2: // 상품등록

				printInsertProductMenu();
				break;
			case 3: // 상품삭제
				printDeleteProductMenu();
				break;
			case 4: // 품절등록
				System.out.println("품절등록할 상품번호를 입력해주세요. >");
				break;
			case 5: //돌아가기
				return;
			case 6: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);

			}
		}

	}// productControl 메소드 끝

	private static void printUpdateProductMenu() {

		ProductController.selectProducts();

		System.out.println("수정할 상품 번호를 입력해주세요. >");
		int productNumber = Integer.parseInt(scanner.nextLine());
		Product selectProduct = ProductController.selectProductByProductNumber(productNumber);

		while (true) {
			System.out.println("1. 상품이름 변경   2. 상품가격 변경  3. 상품설명 변경  4. 이전으로 돌아가기  5. 프로그램 종료");
			System.out.println("메뉴 선택해주세요 > ");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 상품이름 수정
				System.out.println("수정할 상품 이름은?");
				String name = scanner.nextLine();
				selectProduct.setProductName(name);
				ProductController.updateProduct(selectProduct);
				System.out.println(name);
				break;

			case 2: // 상품가격 변경
				System.out.println("수정할 상품 가격 이름은?");
				int price = scanner.nextInt();
				selectProduct.setProductNumber(price);
				ProductController.updateProduct(selectProduct);
				break;

			case 3: // 상품설명 변경
				System.out.println("수정할 상품 설명은?");
				String detail = scanner.nextLine();
				selectProduct.setProductDetail(detail);
				ProductController.updateProduct(selectProduct);
				break;
			case 4: //돌아가기
				return;
			case 5: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);

			}// switch

		} // while

	}// printUpdateProductMenu 메소드

	private static void printInsertProductMenu() {
		ProductController.selectProducts();

		System.out.print("등록할 상품의 카테고리번호는 ? ");
		String categoryNumber = scanner.nextLine();

		System.out.print("등록할 상품의 이름은 ? ");
		String productName = scanner.nextLine();

		System.out.print("등록할 상품의 가격은 ? ");
		int productPrice = Integer.parseInt(scanner.nextLine());

		System.out.print("등록할 상품의 설명은 ? ");
		String productDetail = scanner.nextLine();

		ProductController.insertProduct(new Product(categoryNumber, productName, productPrice, productDetail));

	}// printInsertProductMenu 메소드 끝

	private static void printDeleteProductMenu() {
		ProductController.selectProducts();

		System.out.println("삭제할 상품번호을 입력해주세요. >");
		int productNumber = Integer.parseInt(scanner.nextLine());
		Product selectProduct = ProductController.selectProductByProductNumber(productNumber);

		if (selectProduct != null) {
			ProductController.deleteProduct(productNumber);
		}

	}// printDeleteProductMenu 메소드

	private static void optionControl() {
		System.out.println("원하는 옵션관리를 선택해주세요 >");
		System.out.println("1. 옵션수정  2. 옵션등록  3. 옵션삭제  4. 이전으로 돌아가기  5. 프로그램 종료");
		while (true) {
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 옵션수정
				printUpdateOptionMenu();
				break;
			case 2: // 옵션등록
				printInsertOptionMenu();
				break;
			case 3: // 옵션삭제
				printDeleteOptionMenu();
				break;
			case 4: //돌아가기
				return;
			case 5: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}

	}// optionControl 메소드 끝

	private static void printUpdateOptionMenu() {
		ProductOptionController.selectProductOptions();
		System.out.println("수정할 옵션 번호를 입력해주세요. >");
		int optionNumber = Integer.parseInt(scanner.nextLine());
		ProductOption selectOption = ProductOptionController.selectProductOptionByOptionNumber(optionNumber);

		while (true) {
			System.out.println("1. 옵션 이름 변경   2. 옵션가격 변경  3. 이전으로 돌아가기  4. 프로그램 종료");
			System.out.println("메뉴 선택해주세요 > ");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 옵션이름 수정
				System.out.println("수정할 옵션의 이름을 입력해주세요. >");
				String name = scanner.nextLine();
				selectOption.setOptionName(name);
				ProductOptionController.updateProductOption(selectOption);
				break;

			case 2: // 옵션가격 변경
				System.out.println("수정할 옵션의 가격을 입력해주세요. >");
				int price = scanner.nextInt();
				selectOption.setOptionPrice(price);
				ProductOptionController.updateProductOption(selectOption);
				break;
			case 3: //돌아가기
				return;
			case 4: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}// switch

		} // while*/
	}

	private static void printInsertOptionMenu() {
		ProductOptionController.selectProductOptions();

		System.out.print("등록할 옵션의 이름은 ? ");
		String optionName = scanner.nextLine();

		System.out.print("등록할 옵션의 가격은 ? ");
		int optionPrice = Integer.parseInt(scanner.nextLine());

		// ProductOptionController.insertProductOption(new ProductOption(categoryNumber,
		// optionName, optionPrice));
	}

	private static void printDeleteOptionMenu() {
		ProductOptionController.selectProductOptions();

		System.out.println("삭제할 옵션의 번호를 입력해주세요. >");
		int optionNumber = Integer.parseInt(scanner.nextLine());
		ProductOption selectOption = ProductOptionController.selectProductOptionByOptionNumber(optionNumber);

		if (selectOption != null) {
			ProductOptionController.deleteProductOption(optionNumber);
		}
	}

	private static void storeControl() {

		System.out.println("수정할 항목을 선택해주세요. >");
		System.out.println("1. 매니저 비밀번호 변경   2. 가게이름 변경  3. 이전으로 돌아가기  4. 프로그램 종료");
		int menu = Integer.parseInt(scanner.nextLine());
		while (true) {
			switch (menu) {
			case 1:
				System.out.println("변경할 비밀번호는? ");
				String managerPw = scanner.nextLine();
				ManagementController.updatePassword(managerPw);
				break;

			case 2:
				System.out.println("변경할 가게이름은? ");
				String storeName = scanner.nextLine();
				ManagementController.updateStoreName(storeName);
				break;
				
			case 3:
				return;
				
			case 4: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);

			}
			break;
		}
	}

}
