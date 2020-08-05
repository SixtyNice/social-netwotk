package com.accenture.socialnetwork.Services.PostsService;

import com.accenture.socialnetwork.Entity.PostEntity;

public interface PostsService {

    PostEntity createPost(PostEntity post);

    boolean deletePost(int postId);

    boolean updatePost(int postId, String newText);

    PostEntity getPost(int postId);

}
