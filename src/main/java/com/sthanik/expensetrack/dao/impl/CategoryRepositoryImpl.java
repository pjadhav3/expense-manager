package com.sthanik.expensetrack.dao.impl;

import com.sthanik.expensetrack.dao.CategoryRepository;
import com.sthanik.expensetrack.dao.DBConnection;
import com.sthanik.expensetrack.model.CategoryDetails;
import com.sthanik.expensetrack.model.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public CategoryDetails getCategory(int categoryId) {
        String getUser = "SELECT category_id,category_name,details \n" +
                "FROM category\n" +
                "WHERE category_id = ?\n" +
                ";";
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(getUser);
            ps.setInt(1,categoryId);


            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                CategoryDetails category = new CategoryDetails();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDetails(rs.getString("details"));

                return category;
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
