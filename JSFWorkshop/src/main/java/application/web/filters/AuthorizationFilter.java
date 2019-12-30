package application.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/home.jsf", "/add-job.jsf", "/job-details.jsf", "/delete-job.jsf"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String isLogged = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("username");

        if (isLogged == null){
            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsf");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}

