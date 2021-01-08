package com.sthanik.expensetrack.service;

import com.sthanik.expensetrack.model.CategoryDetails;

public interface CategoryService {
    CategoryDetails getCategory(int categoryId);
    boolean isValidCategory(int categoryId);
}
