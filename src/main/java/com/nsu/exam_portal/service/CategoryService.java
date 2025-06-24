package com.nsu.exam_portal.service;

import com.nsu.exam_portal.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);
     List<Category> getAllCategory();
}
