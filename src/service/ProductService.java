package service;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import dto.Product;
import exception.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDAO productDAO = new ProductDAOImpl();
    private List<Product> soldOutProductList = new ArrayList<>();

    /**
     * 전체 상품조회
     */
    public List<Product> selectProducts() throws NotFoundException, SQLException {
        List<Product> list = productDAO.selectProducts();
        List<Product> productList = new ArrayList<>();
        // productList를 전체 체크하면서 soldOutProudcList와 동일한 Product가 있으면 제외하고, 없으면 productList에 넣음
        // .equals 오버라이딩해서 Product의 productID가 동일한지 체크하면 됨
        // productList를 return해야 함
        if (productList.size() == 0) throw new NotFoundException("현재 상품이 없습니다.");
        return productList;
    }

    public Product selectProductByProductNumber(int productNumber) {
        return null;
    }

    public void insertProduct(Product product) {

    }

    public void updateProduct(Product product) {

    }

    public void deleteProduct(int productNumber) {

    }

    public void addSoldOutProduct(Product product){

    }

    public void deleteSoldOutProduct(Product product){

    }

    public List<Product> showSoldOutProductList(){
        return soldOutProductList;
    }
}


