package com.sthanik.expensetrack.service.impl;

import com.sthanik.expensetrack.dao.CategoryRepository;
import com.sthanik.expensetrack.model.CategoryDetails;
import com.sthanik.expensetrack.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDetails getCategory(int categoryId) {

        CategoryDetails category = categoryRepository.getCategory(categoryId) ;

        return category;
    }

    @Override
    public boolean isValidCategory(int categoryId) {
        if(getCategory(categoryId) == null)
            return false;
        return true;
    }
}
