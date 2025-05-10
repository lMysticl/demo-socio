package demo;

import com.social.model.dao.user.User;
import com.social.model.dao.Photo;
import com.social.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * DEMO controller: example of working with user photos in SocioDate
 */
@RestController
@RequestMapping("/demo/photos")
@RequiredArgsConstructor
@Slf4j
public class DemoPhotoController {
    private final PhotoService photoService;

    /**
     * Upload a photo (demo)
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file) {
        Photo photo = photoService.savePhoto(user.getUserId(), file);
        return ResponseEntity.ok(photo);
    }

    /**
     * Get all user photos (demo)
     */
    @GetMapping("/my")
    public ResponseEntity<List<Photo>> getMyPhotos(@AuthenticationPrincipal User user) {
        List<Photo> photos = photoService.getPhotosByUserId(user.getUserId());
        return ResponseEntity.ok(photos);
    }
}
