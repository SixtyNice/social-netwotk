package com.accenture.socialnetwork.DAO;

import com.accenture.socialnetwork.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDAO extends JpaRepository<PostEntity, Integer> {

    @Query(nativeQuery = true,value = "SELECT post_id FROM user_posts WHERE user_id=?1")
    List<PostEntity> getUserPosts(int userId);



}
