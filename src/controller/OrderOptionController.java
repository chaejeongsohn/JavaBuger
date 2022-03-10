package controller;

import dto.OrderOption;
import service.OrderOptionService;
import utils.DbUtils;
import view.FailView;

import java.sql.SQLException;
import java.util.List;

public class OrderOptionController {
    static OrderOptionService orderOptionService = new OrderOptionService();

    public static void selectOrderOptions() throws SQLException {
    }

    public static void selectOrderOptionByNumber(int orderOptionNo) throws SQLException {
    }

    public static void insertOrderOption(List<OrderOption> orderOptionList) throws SQLException {
        try {
            orderOptionService.insertOrderOption(DbUtils.getConnection(), orderOptionList);
        } catch (SQLException e) {
            FailView.errorMessage(e.getMessage());
        }
    }

    public static void deleteOrderOption(int orderOptionNo) throws SQLException {
    }

    public static void updateOrderOption(OrderOption orderOption) throws SQLException {
    }

}
