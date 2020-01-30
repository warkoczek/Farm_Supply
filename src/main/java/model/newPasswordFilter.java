package model;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.beans.Statement;
import java.io.IOException;
import java.sql.ResultSet;

@WebFilter("/loginValidator")
public class newPasswordFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String user = req.getParameter("user");
        String password = req.getParameter("password");
        String message = "I am sorry, your password should be longer than 5 characters, contain at least one capital letter and one digit";
        String errPage = "createAccount.jsp";

        if(password == null){
            chain.doFilter(request, response);
        }



        if(password.length()<5 || !containsNumber(password) || !containsCapitalL(password)){
            request.setAttribute("user",user);
            request.setAttribute("pErrMsg",message);
            RequestDispatcher rd = request.getRequestDispatcher(errPage);
            rd.include(request, response);

        }else{
            chain.doFilter(request, response);
        }
    }

    private boolean containsCapitalL(String password) {

        return password.chars().anyMatch(Character::isUpperCase);


    }

    private boolean containsNumber(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    @Override
    public void destroy() {

    }
}
