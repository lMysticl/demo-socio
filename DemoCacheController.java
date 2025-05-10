/**
 * DemoCacheController is a portfolio demonstration of a REST API controller
 * for cache operations, inspired by the SociolDate application. It showcases
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
@RequestMapping("/demo/cache")
public class DemoCacheController {
    private static final Logger logger = LoggerFactory.getLogger(DemoCacheController.class);
    private final DemoCache cache;

    public DemoCacheController(DemoCache cache) {
        this.cache = cache;
    }

    /**
     * Store a value in the cache.
     * @param key cache key (must not be blank)
     * @param value cache value (must not be blank)
     * @return confirmation message
     */
    @PostMapping("/put")
    public ResponseEntity<String> put(@RequestParam @NotBlank String key, @RequestParam @NotBlank String value) {
        if (key.isBlank() || value.isBlank()) {
            logger.warn("Attempt to put blank key or value into cache");
            return ResponseEntity.badRequest().body("Key and value must not be blank");
        }
        cache.put(key, value);
        logger.info("Cached key: {} with value: {}", key, value);
        return ResponseEntity.ok("Cached: " + key + " = " + value);
    }

    /**
     * Retrieve a value from the cache.
     * @param key cache key
     * @return value or error if not found
     */
    @GetMapping("/get")
    public ResponseEntity<String> get(@RequestParam @NotBlank String key) {
        if (key.isBlank()) {
            logger.warn("Attempt to get with blank key");
            return ResponseEntity.badRequest().body("Key must not be blank");
        }
        String value = cache.get(key);
        if (value != null) {
            return ResponseEntity.ok("Value: " + value);
        } else {
            logger.info("Cache miss for key: {}", key);
            return ResponseEntity.badRequest().body("Key not found in cache");
        }
    }

    /**
     * Invalidate a cache entry by key.
     * @param key cache key
     * @return confirmation message
     */
    @DeleteMapping("/invalidate")
    public ResponseEntity<String> invalidate(@RequestParam @NotBlank String key) {
        if (key.isBlank()) {
            logger.warn("Attempt to invalidate blank key");
            return ResponseEntity.badRequest().body("Key must not be blank");
        }
        cache.invalidate(key);
        logger.info("Invalidated key: {}", key);
        return ResponseEntity.ok("Invalidated: " + key);
    }

    /**
     * Clear the entire cache.
     * @return confirmation message
     */
    @DeleteMapping("/clear")
    public ResponseEntity<String> clear() {
        cache.clear();
        logger.info("Cache cleared by request");
        return ResponseEntity.ok("Cache cleared");
    }
}
