package com.sthanik.expensetrack.service;

import com.sthanik.expensetrack.model.ExpenseDetails;
import com.sthanik.expensetrack.resource.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    ExpenseDto saveExpense(ExpenseDto expenseDto);
    List<ExpenseDetails> getAllExpenses(int userId);
}
