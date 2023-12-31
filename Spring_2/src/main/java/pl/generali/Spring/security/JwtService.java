package pl.generali.Spring.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final AuthenticationManager authenticationManager;

    public String generateToken(AuthLoginRequest request, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));

        User user = (User) authentication.getPrincipal();

        String[] permissions = user.getUserRole().getGrantedAuthorities()
                .stream()
                .map(permission -> permission.getAuthority()).toArray(value -> new String[value]);

        Algorithm algorithm = Algorithm.HMAC256("key-generali");
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withArrayClaim("permissions", permissions)
                .withExpiresAt(new Date(System.currentTimeMillis() + (10 * 60 * 1000))) // 10 minutes
                .sign(algorithm);
        response.addHeader("jwt-auth-generali", token);
        return token;
    }
}
