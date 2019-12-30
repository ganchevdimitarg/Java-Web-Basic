package application.web.filter;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/view/index.jsf", "/view/register.jsf", "/view/login.jsf"})
public class Authorization implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String id = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("userID");

        if (id != null){
            ((HttpServletResponse) servletRequest).sendRedirect("/view/home.jsf");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
