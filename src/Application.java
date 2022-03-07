import utils.SampleUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");
        Connection con =SampleUtils.getConnection();
        System.out.println(con);
        System.out.println("made it here");
    }
}
