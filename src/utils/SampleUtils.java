package utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class SampleUtils {
    private static Properties proFile = new Properties();



    static {
        try {
            proFile.load(new FileInputStream("src/resources/dbInfo.properties"));
            //proFile.load(new FileInputStream("board.properties"));

            Class.forName(proFile.getProperty("driverName"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Properties getProFile() {
        return proFile;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                proFile.getProperty("url"),
                proFile.getProperty("userName"),
                proFile.getProperty("userPass"));
    }


    public static void close(Connection con, Statement st, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(st != null) st.close();
            if(con != null) con.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
