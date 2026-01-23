package es.artyhub.banco_back.spring.filters;

import es.artyhub.banco_back.domain.model.Cliente;
import es.artyhub.banco_back.domain.service.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

@Component
@Order(0)
public class LoginFilter implements Filter {

    private final AuthService authService;

    public LoginFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        List<String> unprotectedPaths = List.of(
                "/api/login",
                "/api/islogged"
        );

        String header = req.getHeader("authorization");
        String token = null;

        if (header != null) {
            if (header.startsWith("Bearer ")) {
                token = header.substring(7);
            } else {
                token = header;
            }
        }
        Cliente cliente = null;
        if(token != null){
            cliente = authService.getClienteByToken(token);
        }

        if(cliente == null && !unprotectedPaths.contains(req.getRequestURI())){
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
