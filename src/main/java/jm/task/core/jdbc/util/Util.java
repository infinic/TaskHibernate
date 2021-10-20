package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // DB connections settings...
    private static final String url = "jdbc:mysql://ds.umc.local:3306/TaskJDBC";
    private static final String user = "jm";
    private static final String password = "";

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
