package com.sthanik.expensetrack.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
public class ExpenseDetails {
    private int expenseId;
    private int userId;
    private CategoryDetails category;
    private String vendorName;
    private BigDecimal amount;
    private String expenseDate;
}
