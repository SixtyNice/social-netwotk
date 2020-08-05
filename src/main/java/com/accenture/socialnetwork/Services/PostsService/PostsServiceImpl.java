package com.accenture.socialnetwork.Services.PostsService;

import com.accenture.socialnetwork.DAO.PostDAO;
import com.accenture.socialnetwork.Entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostDAO postDAO;

    @Override
    @Transactional
    public PostEntity createPost(PostEntity post) {
        try {
            postDAO.save(post);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deletePost(int postId) {
        try {
            postDAO.deleteById(postId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updatePost(int postId, String newText) {
        Optional<PostEntity> optionalPostEntity = postDAO.findById(postId);
        if (optionalPostEntity.isPresent()) {
            PostEntity post = optionalPostEntity.get();
            post.setText(newText);
            createPost(post);
        }
        return false;
    }

    @Override
    public PostEntity getPost(int postId) {
        Optional<PostEntity> post = postDAO.findById(postId);
        return post.orElse(null);
    }
}
