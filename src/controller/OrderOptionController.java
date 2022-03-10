package controller;

import dto.OrderOption;
import service.OrderOptionService;
import utils.DbUtils;
import view.FailView;

import java.sql.SQLException;

public class OrderOptionController {
    static OrderOptionService orderOptionService = new OrderOptionService();

    public static void selectOrderOptions() throws SQLException {
    }

    public static void selectOrderOptionByNumber(int orderOptionNo) throws SQLException {
    }

    public static void insertOrderOption(OrderOption orderOption) throws SQLException {
        try {
            orderOptionService.insertOrderOption(DbUtils.getConnection(), orderOption);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void deleteOrderOption(int orderOptionNo) throws SQLException {
    }

    public static void updateOrderOption(OrderOption orderOption) throws SQLException {
    }

}
