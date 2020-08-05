package com.accenture.socialnetwork.DAO;

import com.accenture.socialnetwork.Entity.Relationship;
import com.accenture.socialnetwork.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationshipDAO extends JpaRepository<Relationship, Integer> {

    @Query(nativeQuery = true, value = "SELECT id,name FROM relationship WHERE user_one_id=?1 AND user_two_id=?2 OR user_one_id=?2 AND user_two_id=?1")
    Optional<Relationship> findRelationship(UserEntity userOneId, UserEntity userTwoId);

    @Query(nativeQuery = true, value = "SELECT * FROM `relationship`\n" +
            "  WHERE (`user_one_id` = ?1 OR `user_two_id` = ?1)\n" +
            "  AND `status` = 1")
    List<Relationship> getUserFriends(UserEntity user);

}
