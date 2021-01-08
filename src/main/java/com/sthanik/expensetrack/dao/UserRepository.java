package com.sthanik.expensetrack.dao;

import com.sthanik.expensetrack.model.UserDetails;

public interface UserRepository {
    UserDetails getUser(int userId);
}
