package demo;

import com.social.model.dao.user.User;
import com.social.model.dao.Profile;
import com.social.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * DEMO controller: example of working with user profile in SocioDate
 */
@RestController
@RequestMapping("/demo/profile")
@RequiredArgsConstructor
@Slf4j
public class DemoProfileController {
    private final ProfileService profileService;

    /**
     * Get the current user's profile (demo)
     */
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal User user) {
        try {
            Profile profile = profileService.getProfileByUserId(user.getUserId());
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            log.error("[DEMO] Error retrieving profile: {}", e.getMessage());
            return ResponseEntity.badRequest().body("[DEMO] Error: " + e.getMessage());
        }
    }

    /**
     * Update the current user's profile (demo)
     */
    @PutMapping("/me")
    public ResponseEntity<?> updateMyProfile(@AuthenticationPrincipal User user, @RequestBody Profile profileUpdates) {
        Profile updated = profileService.updateProfile(user.getUserId(), profileUpdates);
        return ResponseEntity.ok(updated);
    }
}
