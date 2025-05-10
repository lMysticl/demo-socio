/**
 * DemoJwtController is a portfolio demonstration of a REST API controller
 * for JWT authentication, inspired by the SociolDate application. It showcases
 * professional practices such as constructor injection, logging, and input validation.
 *
 * For full implementation, see the main application codebase.
 */
package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/demo/jwt")
public class DemoJwtController {
    private static final Logger logger = LoggerFactory.getLogger(DemoJwtController.class);
    private final DemoJwtService jwtService;

    public DemoJwtController(DemoJwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Generate a JWT token for a user.
     * @param username user identifier (must not be blank)
     * @return JWT token string
     */
    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestParam @NotBlank String username) {
        if (username.isBlank()) {
            logger.warn("Attempt to generate token with blank username");
            return ResponseEntity.badRequest().body("Username must not be blank");
        }
        String token = jwtService.generateToken(username);
        logger.info("Generated JWT token for user: {}", username);
        return ResponseEntity.ok(token);
    }

    /**
     * Validate a JWT token.
     * @param token JWT token string (must not be blank)
     * @return username if valid, error message if invalid
     */
    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam @NotBlank String token) {
        if (token.isBlank()) {
            logger.warn("Attempt to validate blank token");
            return ResponseEntity.badRequest().body("Token must not be blank");
        }
        String username = jwtService.validateToken(token);
        if (username != null) {
            logger.info("Valid JWT token for user: {}", username);
            return ResponseEntity.ok("Valid token for user: " + username);
        } else {
            logger.warn("Invalid or expired JWT token");
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
    }
}
