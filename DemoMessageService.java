package demo;

import com.social.model.dao.notification.Message;
import com.social.model.dao.user.User;
import com.social.model.dto.MessageDTO;
import com.social.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * DEMO service: example of working with messages (Message) in SocioDate
 * This is a lightweight version of the real MessageService for demonstration purposes.
 */
@Service
@RequiredArgsConstructor
public class DemoMessageService implements MessageService {
    private final MessageService messageService;

    @Override
    public Message saveMessage(Message message) {
        try {
            
            Message saved = messageService.saveMessage(message);
            
            return saved;
        } catch (Exception e) {
            
            throw new demo.exception.MessageOperationException("Ошибка при сохранении сообщения", e);
        }
    }

    @Override
    public Message saveMessage(MessageDTO messageDTO, User user) {
        try {
            
            Message saved = messageService.saveMessage(messageDTO, user);
            
            return saved;
        } catch (Exception e) {
            
            throw new demo.exception.MessageOperationException("Ошибка при сохранении сообщения (DTO)", e);
        }
    }

    @Override
    @Async
    public CompletableFuture<Message> saveMessageAsync(MessageDTO messageDTO, User user) {
        
        return messageService.saveMessageAsync(messageDTO, user)
                .whenComplete((msg, ex) -> {
                    if (ex != null) {
                        
                    } else {
                        
                    }
                });
    }

    @Override
    public List<Message> getMessagesByChatId(ObjectId chatId, Pageable pageable) {
        
        List<Message> messages = messageService.getMessagesByChatId(chatId, pageable);
        
        return messages;
    }

    @Override
    @Async
    public CompletableFuture<List<Message>> getMessagesByChatIdAsync(ObjectId chatId, Pageable pageable) {
        return messageService.getMessagesByChatIdAsync(chatId, pageable);
    }

    @Override
    public void markMessagesAsRead(ObjectId chatId, ObjectId userId) {
        messageService.markMessagesAsRead(chatId, userId);
    }

    @Override
    @Async
    public CompletableFuture<Void> markMessagesAsReadAsync(ObjectId chatId, ObjectId userId) {
        return messageService.markMessagesAsReadAsync(chatId, userId);
    }

    @Override
    public List<Message> findUnreadMessages(ObjectId userId) {
        
        List<Message> unread = messageService.findUnreadMessages(userId);
        
        return unread;
    }

    @Override
    public CompletableFuture<List<Message>> findUnreadMessagesAsync(ObjectId userId) {
        return messageService.findUnreadMessagesAsync(userId);
    }

    @Override
    public List<Message> getAllMessagesByChatId(ObjectId chatId) {
        return messageService.getAllMessagesByChatId(chatId);
    }

    @Override
    public long getMessageCount(ObjectId chatId) {
        return messageService.getMessageCount(chatId);
    }

    @Override
    public java.util.concurrent.Executor getMessageTaskExecutor() {
        return messageService.getMessageTaskExecutor();
    }

    // --- Advanced: Strategy Pattern for Message Formatting ---
    private MessageFormatter messageFormatter = new PlainTextFormatter();

    /**
     * Set the message formatting strategy at runtime.
     */
    public void setMessageFormatter(MessageFormatter formatter) {
        this.messageFormatter = formatter;
        
    }

    /**
     * Format a message using the current strategy.
     */
    public String formatMessage(String message) {
        
        return messageFormatter.format(message);
    }

    /**
     * Strategy interface for message formatting.
     */
    public interface MessageFormatter {
        String format(String message);
    }

    /**
     * Plain text formatting strategy.
     */
    public static class PlainTextFormatter implements MessageFormatter {
        public String format(String message) {
            return message;
        }
    }
    /**
     * Uppercase formatting strategy.
     */
    public static class UppercaseFormatter implements MessageFormatter {
        public String format(String message) {
            return message.toUpperCase();
        }
    }
    /**
     * Markdown formatting strategy.
     */
    public static class MarkdownFormatter implements MessageFormatter {
        public String format(String message) {
            return "**" + message + "**";
        }
    }
}

