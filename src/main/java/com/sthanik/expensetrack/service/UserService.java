package com.sthanik.expensetrack.service;

import com.sthanik.expensetrack.model.UserDetails;

public interface UserService {
    UserDetails getUser(int userId);
    boolean isValidUser(int userId);
}
