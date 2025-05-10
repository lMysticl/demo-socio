package demo;

import com.social.model.dao.Profile;
import com.social.service.ProfileService;
import demo.exception.ProfileUpdateException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

/**
 * DEMO service: example of working with user profile (Profile) in SocioDate
 * Lightweight wrapper over the real ProfileService.
 */
@Service
public class DemoProfileService {
    private final ProfileService profileService;

    public DemoProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public Profile getProfileByUserId(ObjectId userId) {
        return profileService.getProfileByUserId(userId);
    }

    public Profile updateProfile(ObjectId userId, Profile profileUpdates) {
        return profileService.updateProfile(userId, profileUpdates);
    }
}
