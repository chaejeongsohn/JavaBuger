package view;

import java.sql.SQLException;

import exception.NotFoundException;
import view.menu.RoleMenuView;

public class StartView {
    public static void main(String[] args)  {
        RoleMenuView.roleMenu();
        System.out.println("------------------------------");
    }
}


