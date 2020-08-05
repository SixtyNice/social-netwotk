package com.accenture.socialnetwork.Services.LoginService;

import com.accenture.socialnetwork.DAO.UserDAO;
import com.accenture.socialnetwork.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean login(String login, String password) {
        Optional<UserEntity> user = userDAO.findByLogin(login);
        return user.map(userEntity -> userEntity.getPassword().equals(password)).orElse(false);
    }
}
