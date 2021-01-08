package com.sthanik.expensetrack.resource.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@ToString
public class ExpenseDto {
    private int expenseId;
    private int userId;
    private int categoryId;
    private String vendorName;
    private BigDecimal amount;
    private String expenseDate;
}
