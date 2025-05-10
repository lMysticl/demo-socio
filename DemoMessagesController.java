package demo;

import com.social.model.dao.notification.Message;
import com.social.model.dao.user.User;
import com.social.model.dto.MessageDTO;
import com.social.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * DEMO controller: example of how working with messages is organized in SocioDate
 * This is a lightweight copy of the real functionality, extracted for demonstration purposes.
 */
@RestController
@RequestMapping("/demo/messages")
@RequiredArgsConstructor
@Slf4j
public class DemoMessagesController {
    private final MessageService messageService;

    /**
     * Get messages by chatId (demo version)
     */
    @GetMapping("/{chatId}")
    public CompletableFuture<ResponseEntity<List<Message>>> getMessagesByChatId(
            @PathVariable String chatId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @AuthenticationPrincipal User currentUser) {
        try {
            ObjectId chatObjectId = new ObjectId(chatId);
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
            return messageService.getMessagesByChatIdAsync(chatObjectId, pageRequest)
                    .thenApply(ResponseEntity::ok)
                    .exceptionally(throwable -> {
                        log.error("[DEMO] Error retrieving messages: {}", throwable.getMessage());
                        return ResponseEntity.badRequest().build();
                    });
        } catch (IllegalArgumentException e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }
    }

    /**
     * Save a new message (demo version)
     */
    @PostMapping("/{chatId}")
    public CompletableFuture<ResponseEntity<Message>> saveMessage(
            @PathVariable String chatId,
            @RequestBody MessageDTO messageDTO,
            @AuthenticationPrincipal User user) {
        try {
            messageDTO.setChatId(chatId);
            return messageService.saveMessageAsync(messageDTO, user)
                    .thenApply(ResponseEntity::ok)
                    .exceptionally(throwable -> {
                        log.error("[DEMO] Error saving message: {}", throwable.getMessage());
                        return ResponseEntity.badRequest().build();
                    });
        } catch (Exception e) {
            log.error("[DEMO] Error saving message: {}", e.getMessage());
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
        }
    }
}
