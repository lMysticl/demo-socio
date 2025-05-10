package demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PhotoUploadException.class)
    public ResponseEntity<?> handlePhotoUpload(PhotoUploadException ex) {
        return ResponseEntity.badRequest().body("[DEMO] Error during photo upload: " + ex.getMessage());
    }
    @ExceptionHandler(ProfileUpdateException.class)
    public ResponseEntity<?> handleProfileUpdate(ProfileUpdateException ex) {
        return ResponseEntity.badRequest().body("[DEMO] Error during profile update: " + ex.getMessage());
    }
    @ExceptionHandler(MessageOperationException.class)
    public ResponseEntity<?> handleMessageOperation(MessageOperationException ex) {
        return ResponseEntity.badRequest().body("[DEMO] Error during message operation: " + ex.getMessage());
    }
    @ExceptionHandler(LikeOperationException.class)
    public ResponseEntity<?> handleLikeOperation(LikeOperationException ex) {
        return ResponseEntity.badRequest().body("[DEMO] Error during like operation: " + ex.getMessage());
    }
    @ExceptionHandler(DemoException.class)
    public ResponseEntity<?> handleDemoException(DemoException ex) {
        return ResponseEntity.badRequest().body("[DEMO] Error: " + ex.getMessage());
    }
}
