package com.accenture.socialnetwork.Services.UserService;

import com.accenture.socialnetwork.DAO.PostDAO;
import com.accenture.socialnetwork.DAO.RelationshipDAO;
import com.accenture.socialnetwork.DAO.UserDAO;
import com.accenture.socialnetwork.DTO.UserDTO;
import com.accenture.socialnetwork.Entity.PostEntity;
import com.accenture.socialnetwork.Entity.UserEntity;
import com.accenture.socialnetwork.Mapper.Mapper;
import com.accenture.socialnetwork.Services.PostsService.PostsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RelationshipDAO relationshipDAO;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PostsServiceImpl postsService;

    @Override
    public UserDTO getUserInfo(int id) {
        Optional<UserEntity> user = userDAO.findById(id);
        return user.map(UserDTO::new).orElse(null);
    }


    @Override
    public List<UserDTO> getUserFriends(int id) {
        Optional<UserEntity> user = userDAO.findById(id);
        return user.map(userEntity -> Mapper.friendsMap(relationshipDAO.getUserFriends(userEntity), id)).orElse(null);
    }

    @Override
    public List<PostEntity> getUserPosts(int userId) {
        Optional<UserEntity> userEntityOptional = userDAO.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get();
            return user.getPosts();
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public boolean createPost(PostEntity post, int userId) {

        Optional<UserEntity> userOptional = userDAO.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            List<PostEntity> userPosts = getUserPosts(userId);
            PostEntity postEntity = postsService.createPost(post);
            userPosts.add(postEntity);
            user.setPosts(userPosts);
            userDAO.save(user);
            return true;
        }
        return false;
    }
}
