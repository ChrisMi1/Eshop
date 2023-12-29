package com.eshop.demo.security;
import com.eshop.demo.DAO.UserDAO;
import com.eshop.demo.entity.User;
import com.eshop.demo.service.JWTService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{
    private JWTService jwtService;
    private UserDAO userDAO;

    @Autowired
    public JWTRequestFilter(JWTService jwtService,UserDAO userDAO) {
        this.jwtService = jwtService;
        this.userDAO=userDAO;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            try {
                String username = jwtService.getUsername(token);
                Optional<User> opUser = userDAO.findByUsernameIgnoreCase(username);
                if (opUser.isPresent()) {
                    User user = opUser.get();
                    //user.setAddresses(null);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }catch (JwtException ex){

            }

        }
        filterChain.doFilter(request, response);
    }
}
