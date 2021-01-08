package com.sthanik.expensetrack.service.impl;

import com.sthanik.expensetrack.dao.ExpenseRepository;
import com.sthanik.expensetrack.model.ExpenseDetails;
import com.sthanik.expensetrack.resource.dto.ExpenseDto;
import com.sthanik.expensetrack.service.CategoryService;
import com.sthanik.expensetrack.service.ExpenseService;
import com.sthanik.expensetrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Override
    public ExpenseDto saveExpense(ExpenseDto expenseDto) {
        // perform pre-processing business logic before saving the record in database
        System.out.println("In ExpenseServiceImpl: saveExpense");
        System.out.println(expenseDto);

        //TODO check if userId and categoryId are already present then only save the expense
        //if(!userRepository.isValidUser(expenseDetails.getUserId()) || !isValidcategory()){
        if(!userService.isValidUser(expenseDto.getUserId()))
            throw new RuntimeException("Not valid user");

        if(!categoryService.isValidCategory(expenseDto.getCategoryId()))
            throw new RuntimeException("Not valid category");



        //save the expense details in database
        int expenseId = expenseRepository.saveExpense(expenseDto);


        //  set expenseId in the expenseDetails
        expenseDto.setExpenseId(expenseId);
        return expenseDto;
    }

    @Override
    public List<ExpenseDetails> getAllExpenses(int userId) {

        if(!userService.isValidUser(userId))
            throw new RuntimeException("Not valid user");

        List<ExpenseDetails> expenseDetailsList = expenseRepository.getAllExpenses(userId);

        return expenseDetailsList;
    }
}
