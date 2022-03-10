package controller;

import dto.OrderProduct;
import service.OrderProductService;
import utils.DbUtils;
import view.FailView;

import java.sql.SQLException;
import java.util.List;

public class OrderProductController {
    static OrderProductService orderProductService = new OrderProductService();

    public static void selectOrderProducts() throws SQLException {
    }

    public static void selectOrderProductByNumber(int orderProductNo) throws SQLException {
    }

    public static void insertOrderProduct(OrderProduct orderProduct) throws SQLException {
    }

    public static void deleteOrderProduct(int orderProductNo) throws SQLException {
    }

    public static void updateOrderProduct(OrderProduct orderProduct) throws SQLException {
    }

    public static void insertOrderProductList(List<OrderProduct> orderProductList) {
        try {
            orderProductService.insertOrderProduct(DbUtils.getConnection(), orderProductList);
        } catch (SQLException e) {
            e.printStackTrace();
            FailView.errorMessage(e.getMessage());
        }
    }

}
