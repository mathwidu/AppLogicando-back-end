package br.com.logicando.logicando_backend.config;

import br.com.logicando.logicando_backend.model.UserModel;
import br.com.logicando.logicando_backend.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.Base64;

@Component
public class SecurityFilter implements Filter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();
        String method = request.getMethod();

        // Log para depuração
        System.out.println("👉 METHOD: " + method);
        System.out.println("👉 PATH: " + path);

        // Permitir requisição POST para criação de usuário (sem autenticação)
        if (method.equals("POST") && (path.equals("/usuarios") || path.equals("/usuarios/"))) {
            filterChain.doFilter(request, response);
            return;
        }

        var authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Basic ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header missing or invalid");
            return;
        }

        var base64Credentials = authorization.substring("Basic ".length());
        var credentials = new String(Base64.getDecoder().decode(base64Credentials));
        var values = credentials.split(":", 2);

        if (values.length != 2) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid basic auth format");
            return;
        }

        var username = values[0];
        var password = values[1];

        var user = userRepository.findByUsername(username);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
            return;
        }

        var result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!result.verified) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid password");
            return;
        }

        request.setAttribute("usuarioLogado", user);
        filterChain.doFilter(request, response);
    }
}
