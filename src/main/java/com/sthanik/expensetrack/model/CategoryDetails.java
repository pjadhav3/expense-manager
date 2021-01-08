package com.sthanik.expensetrack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDetails {
    private int categoryId;
    private String categoryName;
    private String details;

    public CategoryDetails(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
