package demo;

import com.social.model.dao.notification.Message;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DEMO repository: example of working with MessageRepository in SocioDate
 * This is a lightweight copy for demonstration purposes.
 */
@Repository
public interface DemoMessageRepository {
    /**
     * Find messages by chatId with pagination (demo)
     */
    Page<Message> findByChatIdWithPagination(ObjectId chatId, Pageable pageable);

    /**
     * Find messages by recipientId and read status (demo)
     */
    List<Message> findByRecipientIdAndIsRead(ObjectId recipientId, boolean isRead);

    /**
     * Find messages by chatId with sorting (demo)
     */
    List<Message> findByChatId(ObjectId chatId, Sort sort);

    /**
     * Find all messages by chatId (demo)
     */
    List<Message> findAllByChatId(ObjectId chatId);
}
