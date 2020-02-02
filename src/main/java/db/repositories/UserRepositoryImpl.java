package db.repositories;

import db.DBConnection;
import model.User;

import java.sql.*;


public class UserRepositoryImpl implements UserRepository {

    private static String DBNAME = "Farm_Supply";
    private static String DBUSER = "root";
    private static String DBPASS = "warkocz";
    private static String INSERT_STMT = "INSERT INTO users(login, md5_val) VALUES(?,?)";

    //get user
    @Override
    public boolean isUserPresent(String login, String passToMD5, String dbUser, String dbPass) throws SQLException, ClassNotFoundException {

        String query = "SELECT FROM users WHERE login = '" + login + "' AND md5_val='" + passToMD5 + "';";
        Connection conn = DBConnection.initializeConnection("farm_supply",dbUser,dbPass);
        Statement stm = null;
        ResultSet rs = null;
        boolean isPresent = false;

        try{
            stm.executeQuery(query);
            isPresent = rs.first();

        }finally{
            conn.close();
        }


        return isPresent;
    }

    //insert user
    @Override
    public String saveUser(String login1 , String password) throws SQLException, ClassNotFoundException {

        try(Connection conn = DBConnection.initializeConnection(DBNAME,DBUSER,DBPASS)){

            PreparedStatement ps = conn.prepareStatement(INSERT_STMT);

            ps.setString(1,login1);
            ps.setString(2, password);

            int rowsAffected = ps.executeUpdate();

        }

        return "Ok";
    }
}
