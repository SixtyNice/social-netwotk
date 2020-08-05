package com.accenture.socialnetwork.Services.UserService;

import com.accenture.socialnetwork.DTO.UserDTO;
import com.accenture.socialnetwork.Entity.PostEntity;
import com.accenture.socialnetwork.Entity.Relationship;
import com.accenture.socialnetwork.Entity.UserEntity;

import java.util.List;

public interface UserService {

    UserDTO getUserInfo(int userId);

    List<UserDTO> getUserFriends(int userId);

    List<PostEntity> getUserPosts(int userId);

    boolean createPost(PostEntity post,int userId);

}
