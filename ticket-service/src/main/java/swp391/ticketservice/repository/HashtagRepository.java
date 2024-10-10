package swp391.ticketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swp391.entity.Hashtag;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Hashtag h WHERE h.id =: hashtagId")
    void deleteHashtagLogic(Integer hashtagId);

    @Query("SELECT H FROM Hashtag AS H WHERE H.isDeleted = FALSE ")
    List<Hashtag> getALl();
}
