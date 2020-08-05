package com.accenture.socialnetwork.Services.RelationshipService;

import com.accenture.socialnetwork.DAO.RelationshipDAO;
import com.accenture.socialnetwork.DAO.UserDAO;
import com.accenture.socialnetwork.Entity.Relationship;
import com.accenture.socialnetwork.Entity.UserEntity;
import com.accenture.socialnetwork.Enum.RelationshipResponse;
import com.accenture.socialnetwork.Enum.RelationshipStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    private final static String FROM_USER = "fromUser";
    private final static String TO_USER = "toUser";
    @Autowired
    private RelationshipDAO relationshipDAO;
    @Autowired
    private UserDAO userDAO;
    private Map<String, UserEntity> users;

    @Override
    @Transactional
    public String addToFriend(int fromUserId, int toUserId) {

        users = findUsers(fromUserId, toUserId);
        if (users != null) {
            UserEntity fromUser = users.get(FROM_USER);
            UserEntity toUser = users.get(TO_USER);
            //Check may be already friends
            try {
                Relationship relationship = new Relationship(fromUser, toUser);
                relationshipDAO.save(relationship);
                return RelationshipResponse.SENT.getResponse();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return RelationshipResponse.NO_USER.getResponse();
        }

        return RelationshipResponse.WENT_WRONG.getResponse();
    }

    @Override
    @Transactional
    public boolean deleteFriend(int fromUserId, int toUserId) {

        users = findUsers(fromUserId, toUserId);
        if (users != null) {
            try {
                Optional<Relationship> relationship = relationshipDAO.findRelationship(users.get(FROM_USER), users.get(TO_USER));

//                relationship.ifPresent(value -> relationshipDAO.delete(value));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean acceptUser(int fromUserId, int toUserId) {
        users = findUsers(fromUserId, toUserId);
        return setStatusOfRelationship(users, RelationshipStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public boolean declineUser(int fromUserId, int toUserId) {
        users = findUsers(fromUserId, toUserId);
        return setStatusOfRelationship(users, RelationshipStatus.DECLINE);
    }

    @Override
    @Transactional
    public boolean blockUser(int fromUserId, int toUserId) {
        users = findUsers(fromUserId, toUserId);
        return setStatusOfRelationship(users, RelationshipStatus.BLOCKED);
    }

    //Find user who send request and who receive
    private Map<String, UserEntity> findUsers(int fromUserId, int toUserId) {
        Optional<UserEntity> fromUser = userDAO.findById(fromUserId);
        Optional<UserEntity> toUser = userDAO.findById(toUserId);
        if (fromUser.isPresent() && toUser.isPresent()) {
            Map<String, UserEntity> users = new HashMap<>();
            users.put(FROM_USER, fromUser.get());
            users.put(TO_USER, toUser.get());
            return users;
        }
        return null;
    }

    //Set status of relationship to user request (accept,decline,blocked)
    private boolean setStatusOfRelationship(Map<String, UserEntity> users, RelationshipStatus status) {
        if (users.size() != 0) {
            Optional<Relationship> relationshipOptional = relationshipDAO.findRelationship(users.get(FROM_USER), users.get(TO_USER));
            if (relationshipOptional.isPresent()) {
                Relationship relationship = relationshipOptional.get();
                relationship.setStatus(status);
                relationshipDAO.save(relationship);
                return true;
            }
        }
        return false;
    }

}
