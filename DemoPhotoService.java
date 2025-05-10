package demo;

import com.social.model.dao.Photo;
import com.social.service.PhotoService;
import demo.exception.PhotoUploadException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * DEMO service: example of working with photos (Photo) in SocioDate
 * Lightweight wrapper over the real PhotoService.
 */
@Service
public class DemoPhotoService {
    private final PhotoService photoService;

    public DemoPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public Photo savePhoto(ObjectId userId, MultipartFile file) {
        return photoService.savePhoto(userId, file);
    }

    public List<Photo> getPhotosByUserId(ObjectId userId) {
        return photoService.getPhotosByUserId(userId);
    }
}
