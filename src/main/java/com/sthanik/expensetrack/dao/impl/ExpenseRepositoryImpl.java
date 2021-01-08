package com.sthanik.expensetrack.dao.impl;

import com.sthanik.expensetrack.dao.DBConnection;
import com.sthanik.expensetrack.dao.ExpenseRepository;
import com.sthanik.expensetrack.model.CategoryDetails;
import com.sthanik.expensetrack.model.ExpenseDetails;
import com.sthanik.expensetrack.resource.dto.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveExpense(ExpenseDto expenseDto) {

        String saveExpense = "INSERT INTO expense(user_id,category_id,vendor_name,amount,expense_date) VALUES (?,?,?,?,?)";

        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        int generatedKey = 0;
        try {
            ps = connection.prepareStatement(saveExpense,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expenseDto.getUserId());
            ps.setInt(2, expenseDto.getCategoryId());
            ps.setString(3, expenseDto.getVendorName());
            ps.setBigDecimal(4, expenseDto.getAmount());
            ps.setDate(5,java.sql.Date.valueOf(expenseDto.getExpenseDate()));

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            System.out.println("Inserted record's ID: " + generatedKey);


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection);
        }

        return generatedKey;
    }

    @Override
    public List<ExpenseDetails> getAllExpenses(int userId) {
        List<ExpenseDetails> expenseList = new ArrayList<>();
        String getAllExpenses = "SELECT e.expense_id,e.user_id,e.category_id,c.category_name,e.vendor_name,e.amount,e.is_active,e.create_date,e.last_update_date \n" +
                "FROM expense e\n" +
                "JOIN category c ON c.category_id = e.category_id\n" +
                "WHERE e.user_id = ?\n" +
                ";";
        Connection connection = DBConnection.getDBConnection();
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(getAllExpenses);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ExpenseDetails expense = new ExpenseDetails();

                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setUserId(rs.getInt("user_id"));
                expense.setCategory(new CategoryDetails(rs.getInt("category_id"),rs.getString("category_name")));
                expense.setVendorName(rs.getString("vendor_name"));
                expense.setAmount(rs.getBigDecimal("amount"));

                expenseList.add(expense);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            DBConnection.closeConnection(connection);
        }

        return expenseList;
    }
}
