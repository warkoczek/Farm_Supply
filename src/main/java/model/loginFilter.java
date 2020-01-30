package model;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/loginValidator")
public class loginFilter implements Filter {

    public loginFilter() {
        System.out.println("I am in my login filter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String login = req.getParameter("user");
        String password = req.getParameter("password");
        String message = "I am sorry, incorrect username. It must be longer than 4 signs. Please try again";
        String errPage = "login.jsp";
        if(login == null){
            chain.doFilter(request, response);

        }
        if(login.length() <=4){
            request.setAttribute("password",password);
            request.setAttribute("errMsg",message);
            RequestDispatcher rd = request.getRequestDispatcher(errPage);
            rd.include(request, response);

        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
