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
public interface UserDAO extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByLogin(String login);

}
