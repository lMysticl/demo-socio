package demo;

import com.social.model.dao.chat.Chat;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * DEMO repository: example of working with ChatRepository in SocioDate
 * This is a lightweight copy for demonstration purposes.
 */
@Repository
public interface DemoChatRepository {
    /**
     * Find a chat by its ID (demo)
     */
    Optional<Chat> findById(ObjectId objectId);

    /**
     * Find a chat by its unique chat ID (demo)
     */
    Optional<Chat> findByUniqChatId(ObjectId uniqChatId);

    /**
     * Find all chats for a user (demo)
     */
    List<Chat> findAllByUserUserId(ObjectId userId);

    /**
     * Save a chat (demo)
     */
    <S extends Chat> S save(S chat);

    /**
     * Find chats for a user with pagination (demo)
     */
    Page<Chat> findByUser(ObjectId userId, Pageable pageable);

    /**
     * Find a chat by user and unique chat ID (demo)
     */
    Optional<Chat> findByUserId(ObjectId userId, ObjectId uniqChatId);

    /**
     * Find chats by two user IDs (demo)
     */
    List<Chat> findChatsByUserIds(ObjectId user1Id, ObjectId user2Id);

    /**
     * Delete all chats by user ID (demo)
     */
    void deleteAllByUserId(ObjectId userId);

    /**
     * Delete chats by two user IDs (demo)
     */
    void deleteChatsByUsers(ObjectId userId1, ObjectId userId2);

    /**
     * Find chats by user ID or participant user ID (demo)
     */
    List<Chat> findByUserIdOrParticipantUserId(ObjectId userId, ObjectId participantUserId);

    /**
     * Find a chat by user and participant IDs (demo)
     */
    Optional<Chat> findByUserIdAndParticipantIdOrParticipantIdAndUserId(ObjectId userId1, ObjectId participantId1);
}
