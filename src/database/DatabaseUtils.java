package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
    public static Connection c;
    private static String db_url = "jdbc:mysql://localhost:3306/BOOK_DB";
    private static String username = "root";
    private static String password = "";

    public static Connection getConnection() throws Exception {
        if (c == null) {
            c = DriverManager.getConnection(db_url, username, password);
        }
        return c;
    }
}