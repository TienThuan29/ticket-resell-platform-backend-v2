package swp391.chatservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.chatservice.entity.RoomChat;

import java.util.Optional;

@Repository
public interface RoomChatRepository extends MongoRepository<RoomChat , ObjectId> {
    @Query("{ 'members': { $all: [?0, ?1] } }")
    Optional<RoomChat> findByTwoMembers(Long member1, Long member2);

    Optional<RoomChat> findByConversationId(Long conversationId);
}
