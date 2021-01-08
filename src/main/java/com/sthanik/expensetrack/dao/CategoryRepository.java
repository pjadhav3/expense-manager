package com.sthanik.expensetrack.dao;

import com.sthanik.expensetrack.model.CategoryDetails;

public interface CategoryRepository {
    CategoryDetails getCategory(int categoryId);
}
