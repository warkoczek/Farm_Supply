package model.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter({"/newLoginValidator", "/loginValidator"})

public class newLoginFilter implements Filter {

    public newLoginFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String user = req.getParameter("user");
        String message = "Username too short. It must be longer than 4 characters and at least one digit. Please, try again.";
        String errPage = "createAccount.jsp";

        if(user == null){
            chain.doFilter(request, response);
        }

        if(user.length()<=4 || !containsNumber(user)){
            request.setAttribute("errMsg",message);
            RequestDispatcher rd = request.getRequestDispatcher(errPage);
            rd.include(request, response);
        }else{
            chain.doFilter(request, response);
        }
    }

    private boolean containsNumber(String user) {
        return user.chars().anyMatch(Character::isDigit);
    }

    @Override
    public void destroy() {

    }

    public static void main(String[] args) {

    }
}
