package web.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import service.security.LoginService;
import web.Filter.Parser.CookieParser;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthorizedFiler implements Filter {

    Logger logger = Logger.getLogger(AuthorizedFiler.class.getName());

    @Autowired
    private LoginService loginService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String token = CookieParser.getToken(httpRequest);

        if (token != null) {
            if (loginService.isAuthorised(token)) {
                logger.info("Authorized check successful");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            logger.info("Unauthorized access to " + httpRequest.getRequestURI());
            httpResponse.sendRedirect("/login");
        }
    }


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
