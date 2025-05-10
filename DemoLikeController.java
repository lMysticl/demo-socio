package demo;

import com.social.model.dao.user.User;
import com.social.service.LikeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * DEMO controller: example of working with likes (Like) in SocioDate
 */
@RestController
@RequestMapping("/demo/likes")
@RequiredArgsConstructor
@Slf4j
public class DemoLikeController {
    private final LikeServiceImpl likeService;

    /**
     * Add a like to a user (demo)
     */
    @PostMapping("/{targetUserId}")
    public ResponseEntity<String> likeUser(@PathVariable String targetUserId, @AuthenticationPrincipal User user) {
        try {
            likeService.likeUser(new ObjectId(targetUserId), user.getUserId());
            return ResponseEntity.ok("[DEMO] Like added successfully!");
        } catch (Exception e) {
            log.error("[DEMO] Error adding like: {}", e.getMessage());
            return ResponseEntity.badRequest().body("[DEMO] Error: " + e.getMessage());
        }
    }

    /**
     * Get the list of liked users (demo)
     */
    @GetMapping("/my")
    public ResponseEntity<?> getLikedUsers(@AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(likeService.getLikedUsers(user.getUserId()));
        } catch (Exception e) {
            log.error("[DEMO] Error retrieving likes: {}", e.getMessage());
            return ResponseEntity.badRequest().body("[DEMO] Error: " + e.getMessage());
        }
    }
}
