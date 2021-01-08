package com.sthanik.expensetrack.dao;

import com.sthanik.expensetrack.model.ExpenseDetails;
import com.sthanik.expensetrack.resource.dto.ExpenseDto;

import java.util.List;

public interface ExpenseRepository{
    int saveExpense(ExpenseDto expenseDto);

    List<ExpenseDetails> getAllExpenses(int userId);
}
