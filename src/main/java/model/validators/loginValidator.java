package model.validators;

import db.DBConnection;
import db.repositories.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/loginValidator")
public class loginValidator extends HttpServlet {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();


    public loginValidator() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String login1 = req.getParameter("user");
        String password1 = req.getParameter("password");

        out.print("Hi user " + login1);
        out.print("With password " + password1);

        String passToMD5 = createMD5(password1);
        out.print("Your pass md5: " + passToMD5);


        String dbUser = "root";
        String dbPass = "warkocz";


        boolean exists = isPresent(login1,passToMD5,dbUser,dbPass);

        if(exists == false){
             resp.sendRedirect("createAccount.jsp");


        }else{
            HttpSession session = req.getSession();
            session.setAttribute("user",login1);
            session.setMaxInactiveInterval(60);
            resp.sendRedirect("products.jsp");
        }

        //zmienne sesyjne




    }

    private boolean isPresent(String login, String md5, String dbUser, String dbPass ) {

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
