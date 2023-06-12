package models;

import java.sql.Connection;
import java.sql.ResultSet;
import database.DatabaseUtils;

public class LoginModel {
    // Kiểm tra đăng nhập
    public boolean checkLogin(String username, String password) throws Exception {
        Connection connection = DatabaseUtils.getConnection();
        String query = "SELECT * FROM  Account";
        ResultSet result = connection.createStatement().executeQuery(query);

        while (result.next()) {
            String usernameString = result.getString(1);
            String passwordString = result.getString(2);
            if (username.equals(usernameString) && password.equals(passwordString)) {
                return true;
            }
        }
        return false;
    }
}