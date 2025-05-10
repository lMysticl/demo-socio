package demo;

import com.social.service.LikeServiceImpl;
import demo.exception.LikeOperationException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * DEMO service: example of working with likes (Like) in SocioDate
 * Lightweight wrapper over the real LikeServiceImpl.
 */
@Service
public class DemoLikeService {
    private final LikeServiceImpl likeService;

    public DemoLikeService(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    public void likeUser(ObjectId targetUserId, ObjectId userId) {
        likeService.likeUser(targetUserId, userId);
    }

    public List<ObjectId> getLikedUsers(ObjectId userId) {
        return likeService.getLikedUsers(userId);
    }
}
