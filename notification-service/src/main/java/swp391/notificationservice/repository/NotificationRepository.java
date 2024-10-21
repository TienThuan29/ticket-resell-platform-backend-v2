package swp391.notificationservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import swp391.notificationservice.entity.Notification;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, ObjectId> {

    List<Notification> getNotificationByReceiverId(Long receiverId);

    List<Notification> findByReceiverIdAndIsReadAndIsDeleted(Long receiverId, Boolean isRead, Boolean isDeleted);

}
