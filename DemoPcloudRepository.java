package demo;

import com.social.model.dao.pcload.Pcloud;
import com.social.model.dao.user.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * DEMO repository: example of working with PcloudRepository in SocioDate
 * This is a lightweight copy for demonstration purposes.
 */
@Repository
public interface DemoPcloudRepository {
    /**
     * Find a Pcloud by its ID (demo)
     */
    Optional<Pcloud> findById(ObjectId id);

    /**
     * Find all Pclouds for a user with eager fetch (demo)
     */
    List<Pcloud> findByUserWithEagerFetch(ObjectId userId);

    /**
     * Find all Pclouds for a user (demo)
     */
    List<Pcloud> findByUser(User user);
}
