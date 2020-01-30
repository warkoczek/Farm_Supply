package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection initializeConnection(String dbname, String user, String password) throws
            SQLException, ClassNotFoundException {

        Connection conn = null;

        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/" + dbname + "?serverTimezone=UTC&allowPublicKeyRetrieval=true";
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbURL,user,password);

        return conn;
    }
}
