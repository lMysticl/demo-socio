package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DemoCache is a portfolio demonstration of a production-inspired cache component
 * from the SociolDate application. It showcases thread-safe in-memory caching
 * using best practices and logging for educational and interview purposes.
 *
 * For full implementation, see the main application codebase.
 */
package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DemoCache {
    private static final Logger logger = LoggerFactory.getLogger(DemoCache.class);
    private final Map<String, String> cache = new ConcurrentHashMap<>();

    /**
     * Put a key-value pair into the cache.
     * @param key the cache key
     * @param value the cache value
     */
    public void put(String key, String value) {
        logger.info("Caching value for key: {}", key);
        cache.put(key, value);
    }

    /**
     * Retrieve a value from the cache.
     * @param key the cache key
     * @return the cached value or null if not present
     */
    public String get(String key) {
        String value = cache.get(key);
        if (value != null) {
            logger.info("Cache hit for key: {}", key);
        } else {
            logger.info("Cache miss for key: {}", key);
        }
        return value;
    }

    /**
     * Invalidate a cache entry by key.
     * @param key the cache key
     */
    public void invalidate(String key) {
        logger.info("Invalidating cache for key: {}", key);
        cache.remove(key);
    }

    /**
     * Clear the entire cache.
     */
    public void clear() {
        logger.info("Clearing all cache entries");
        cache.clear();
    }

    /**
     * Check if a key exists in the cache.
     * @param key the cache key
     * @return true if present, false otherwise
     */
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public void invalidate(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}

