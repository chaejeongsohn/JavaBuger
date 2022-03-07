import utils.SampleUtils;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");
        SampleUtils.getConnection();
        System.out.println("made it here");
    }
}
