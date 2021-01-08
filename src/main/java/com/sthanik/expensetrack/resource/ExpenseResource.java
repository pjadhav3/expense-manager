package com.sthanik.expensetrack.resource;

import com.sthanik.expensetrack.model.ExpenseDetails;
import com.sthanik.expensetrack.resource.dto.ExpenseDto;
import com.sthanik.expensetrack.model.ResponseDetails;
import com.sthanik.expensetrack.model.UserDetails;
import com.sthanik.expensetrack.service.CategoryService;
import com.sthanik.expensetrack.service.ExpenseService;
import com.sthanik.expensetrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseResource {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseDetails getAllExpenses(@RequestBody UserDetails user, HttpServletResponse response){
        System.out.println("In ExpenseResource : getAllExpenses");
        List<ExpenseDetails> expenseDetailsList = null;

        //Validate userId
        List<String> errors = validateUser(user);
        if (errors.size() > 0) {
            int responseCode = HttpStatus.BAD_REQUEST.value();
            response.setStatus(responseCode);
            return generateErrorResponse(responseCode, errors);
        }

        // call business logic to get all expenses
        try {
            expenseDetailsList = expenseService.getAllExpenses(user.getUserId());

        }
        catch(RuntimeException re){
            int responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            response.setStatus(responseCode);
            return generateErrorResponse(responseCode, Arrays.asList(re.getMessage()));
        }
        // return CREATED 201 as success for expense creation
        int responseCode = HttpStatus.OK.value();
        response.setStatus(responseCode);
        return generateSuccessResponse(responseCode, expenseDetailsList);
    }

    @PostMapping
    public ResponseDetails saveExpense(@RequestBody ExpenseDto expenseDto, HttpServletResponse response) {
        System.out.println("In ExpenseResource : saveExpense");
        System.out.println(expenseDto);

        // validate expense details
        List<String> errors = validateExpense(expenseDto);
        if (errors.size() > 0) {
            int responseCode = HttpStatus.BAD_REQUEST.value();
            response.setStatus(responseCode);
            return generateErrorResponse(responseCode, errors);
        }

        // call business logic to save expense data
        try {
            expenseDto = expenseService.saveExpense(expenseDto);
        }
        catch(RuntimeException re){
            int responseCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            response.setStatus(responseCode);
            return generateErrorResponse(responseCode, Arrays.asList(re.getMessage()));
        }

        // return CREATED 201 as success for expense creation
        int responseCode = HttpStatus.CREATED.value();
        response.setStatus(responseCode);
        return generateSuccessResponse(responseCode, expenseDto);
    }

    // TODO implement GET /expense api to return all expenses from the system
    public ResponseDetails fetchExpenses() {
        return null;
    }

    private ResponseDetails generateErrorResponse(int responseCode, List<String> errors) {
        ResponseDetails responseDetails = new ResponseDetails(responseCode);
        responseDetails.setErrors(errors);
        return responseDetails;
    }

    private ResponseDetails generateSuccessResponse(Object data) {
        return generateSuccessResponse(HttpStatus.OK.value(), data);
    }

    private ResponseDetails generateSuccessResponse(int responseCode, Object data) {
        ResponseDetails responseDetails = new ResponseDetails(responseCode);
        responseDetails.setData(data);
        return responseDetails;
    }

    private List<String> validateUser(UserDetails user) {
        List<String> errors = new ArrayList<>();
        if (user.getUserId() == 0) {
            errors.add("user id should not be null! ;");
        }

        //TODO check validation for other attributes as well
        return errors;
    }

    private List<String> validateExpense(ExpenseDto expenseDto) {
        List<String> errors = new ArrayList<>();
        if (expenseDto.getUserId() == 0) {
            errors.add("user id should not be null! ;");
        }
        if (expenseDto.getCategoryId() == 0) {
            errors.add("category id should not be null! ;");
        }
        if (expenseDto.getVendorName() == null) {
            errors.add("vendor name should not be null! ;");
        }
        if (expenseDto.getAmount() == null) {
            errors.add("amount should not be null! ;");
        }
        if (expenseDto.getExpenseDate() == null) {
            errors.add("Expense date should not be null! ;");
        }
        return errors;
    }

}
