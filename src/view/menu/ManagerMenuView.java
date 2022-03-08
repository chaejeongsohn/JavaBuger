package view.menu;

import java.util.Scanner;

import controller.ProductController;
import controller.ProductOptionController;
import dto.Product;
import dto.ProductOption;
import service.ProductService;

public class ManagerMenuView {
	private static Scanner scanner = new Scanner(System.in);

	public static void managerMenu() {
		while (true) {
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
				break;
			case 5: // 프로그램 종료
				System.exit(0);
			}
		}
	}

	private static void printManagerMenu() {
		System.out.println("---------------------");
		System.out.println("1. 상품관리  2. 옵션관리  3. 매출관리  4. 매점관리  5. 프로그램종료");
	}

	private static void productControl() {
		System.out.println("원하는 상품관리를 선택해주세요 >");
		System.out.println("1. 상품수정  2. 상품등록  3. 상품삭제  4. 품절등록");
		while (true) {
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 상품수정
				printUpdateProductMenu();

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

			}
		}

	}// productControl 메소드 끝

	private static void printUpdateProductMenu() {

		ProductController.selectProducts();

		System.out.println("수정할 상품 번호를 입력해주세요. >");
		int productNumber = Integer.parseInt(scanner.nextLine());
		Product selectProduct = ProductController.selectProductByProductNumber(productNumber);

		while (true) {
			System.out.println("1. 상품이름 변경   2. 상품가격 변경  3. 상품설명 변경 ");
			System.out.println("메뉴 선택해주세요 > ");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 상품이름 수정
				System.out.println("수정할 상품 이름를 입력해주세요. >");
				String name = scanner.nextLine();
				selectProduct.setProductName(name);
				ProductController.updateProduct(selectProduct);
				break;

			case 2: // 상품가격 변경
				System.out.println("수정할 상품 가격를 입력해주세요. >");
				int price = scanner.nextInt();
				selectProduct.setProductNumber(price);
				ProductController.updateProduct(selectProduct);
				break;

			case 3: // 상품설명 변경
				System.out.println("수정할 상품 설명을 입력해주세요. >");
				String detail = scanner.nextLine();
				selectProduct.setProductDetail(detail);
				ProductController.updateProduct(selectProduct);
				break;

			}// switch

		} // while

	}// printUpdateProductMenu 메소드

	private static void printInsertProductMenu() {
		ProductController.selectProducts();

		System.out.print("등록할 상품번호는 ? ");
		int productNumber = Integer.parseInt(scanner.nextLine());

		System.out.print("등록할 상품카테고리번호는 ? ");
		String categoryNumber = scanner.nextLine();

		System.out.print("등록할 상품이름은 ? ");
		String productName = scanner.nextLine();

		System.out.print("등록할 상품가격은 ? ");
		int productPrice = Integer.parseInt(scanner.nextLine());

		System.out.print("등록할 상품설명은 ? ");
		String productDetail = scanner.nextLine();

		ProductController
				.insertProduct(new Product(productNumber, categoryNumber, productName, productPrice, productDetail));

	}// printInsertProductMenu 메소드 끝

	private static void printDeleteProductMenu() {
		ProductController.selectProducts();

		System.out.println("삭제할 상품번호을 선택해주세요. >");
		int productNumber = Integer.parseInt(scanner.nextLine());
		Product selectProduct = ProductController.selectProductByProductNumber(productNumber);

		if (selectProduct != null) {
			ProductController.deleteProduct(productNumber);
		}

	}// printDeleteProductMenu 메소드

	private static void optionControl() {
		System.out.println("원하는 옵션관리를 선택해주세요 >");
		System.out.println("1. 옵션수정  2. 옵션등록  3. 옵션삭제 ");
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

			}
		}

	}// optionControl 메소드 끝

	private static void printUpdateOptionMenu() {
		ProductOptionController.selectProductOptions();
		System.out.println("수정할 옵션 번호를 입력해주세요. >");
		int optionNumber = Integer.parseInt(scanner.nextLine());
		ProductOption selectOption = ProductOptionController.selectProductOptionByOptionNumber(optionNumber);

		while (true) {
			System.out.println("1. 옵션이름 변경   2. 옵션가격 변경  ");
			System.out.println("메뉴 선택해주세요 > ");
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1: // 옵션이름 수정
				System.out.println("수정할 옵션 이름를 입력해주세요. >");
				String name = scanner.nextLine();
				selectOption.setOptionName(name);
				ProductOptionController.updateProductOption(selectOption);
				break;

			case 2: // 옵션가격 변경
				System.out.println("수정할 옵션 가격를 입력해주세요. >");
				int price = scanner.nextInt();
				selectOption.setOptionPrice(price);
				ProductOptionController.updateProductOption(selectOption);
				break;

			}// switch

		} // while*/
	}

	private static void printInsertOptionMenu() {
		ProductOptionController.selectProductOptions();
		
		System.out.print("등록할 옵션번호는 ? ");
		int optionNumber = Integer.parseInt(scanner.nextLine());

		System.out.print("등록할 옵션카테고리번호는 ? ");
		String categoryNumber = scanner.nextLine();

		System.out.print("등록할 옵션이름은 ? ");
		String optionName = scanner.nextLine();

		System.out.print("등록할 옵션가격은 ? ");
		int optionPrice = Integer.parseInt(scanner.nextLine());

		ProductOptionController
				.insertProductOption(new ProductOption(optionNumber, categoryNumber, optionName, optionPrice));
	}

	private static void printDeleteOptionMenu() {
		ProductOptionController.selectProductOptions();

		System.out.println("삭제할 옵션번호을 선택해주세요. >");
		int optionNumber = Integer.parseInt(scanner.nextLine());
		ProductOption selectOption = ProductOptionController.selectProductOptionByOptionNumber(optionNumber);

		if (selectOption != null) {
			ProductOptionController.deleteProductOption(optionNumber);
		}
	}

}
