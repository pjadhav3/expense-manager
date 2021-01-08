package com.sthanik.expensetrack.service.impl;

import com.sthanik.expensetrack.dao.UserRepository;
import com.sthanik.expensetrack.model.UserDetails;
import com.sthanik.expensetrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails getUser(int userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public boolean isValidUser(int userId) {
        UserDetails user = userRepository.getUser(userId);
        if(user==null)
            return false;
        return true;
    }
}
