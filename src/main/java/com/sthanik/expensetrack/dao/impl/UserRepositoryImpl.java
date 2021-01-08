package com.sthanik.expensetrack.dao.impl;

import com.sthanik.expensetrack.dao.DBConnection;
import com.sthanik.expensetrack.dao.UserRepository;
import com.sthanik.expensetrack.model.UserDetails;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserDetails getUser(int userId) {

        String getUser = "SELECT user_id,first_name,last_name,birth_year,email,pass,phone,zip \n" +
                "FROM users\n" +
                "WHERE user_id = ?\n"+
                "AND is_active = TRUE"+
                ";";
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(getUser);
            ps.setInt(1,userId);


            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                UserDetails user = new UserDetails();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));

                return user;
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection);
        }

        return null;
    }
}
