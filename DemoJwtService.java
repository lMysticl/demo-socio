/**
 * DemoJwtService is a portfolio demonstration of JWT token generation and validation
 * inspired by the SociolDate application. This is a simplified, educational example
 * for recruiters and interviewers. In production, use secure secret management and
 * advanced validation.
 *
 * For full implementation, see the main application codebase.
 */
package demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class DemoJwtService {
    /**
     * WARNING: Demo secret key. Never hardcode secrets in production.
     */
    private static final String SECRET_KEY = "demo_secret_key_123";
    private static final long EXPIRATION_MS = 60 * 60 * 1000; // 1 hour

    /**
     * Generate a JWT token for the provided username.
     * @param username the user identifier
     * @return JWT token string
     */
    public String generateToken(String username) {
        logger.info("Generating JWT token for user: {}", username);
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Validate a JWT token and extract the username.
     * @param token JWT token string
     * @return username if valid, null if invalid or expired
     */
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
