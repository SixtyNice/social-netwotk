package com.accenture.socialnetwork.Services.RegistrationService;

import com.accenture.socialnetwork.DAO.UserDAO;
import com.accenture.socialnetwork.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public boolean registration(UserEntity user) {
        try {
            userDAO.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
