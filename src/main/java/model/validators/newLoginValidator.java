package model.validators;

import db.DBConnection;
import db.repositories.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/newLoginValidator")
public class newLoginValidator extends HttpServlet {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();


    public newLoginValidator() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login1 = req.getParameter("user");
        String password1 = req.getParameter("password");

        String md5 = createMD5(password1);

        boolean exists = isPresent(login1,md5);

        if(exists == false){
            try {
                userRepository.saveUser(login1, md5);
            } catch (SQLException e) {
                System.out.println("Can not save");
            } catch (ClassNotFoundException e) {
                System.out.println("No such a class");
            }
            resp.sendRedirect("welcomeNewUser.jsp");

        }else{
            resp.sendRedirect("userAlreadyExists.jsp");
        }

    }

    private boolean isPresent(String login, String md5) {

        String dbUser = "root";
        String dbPass = "warkocz";

        String query = "SELECT * FROM users where login = '" + login + "' AND md5_val = '" + md5 + "';";
        boolean isAvailable = false;
        try{
            Connection conn = DBConnection.initializeConnection("farm_supply",dbUser,dbPass);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.absolute(1)){
                isAvailable = true;
            }else{
                isAvailable = false;
            }


        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Exception: " + e.getMessage());

        }

        return isAvailable;

    }

    private String createMD5(String password1) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password1.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }

            String md5 = sb.toString().toUpperCase();
            return md5;
        }catch (NoSuchAlgorithmException e){

            return "";
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
