package org.rean.crud.service;

import org.rean.crud.model.dto.CategoriesDto;
import org.rean.crud.model.request.CategoryRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface CategoryService {
    ApiResponse<CategoriesDto> createCategory(CategoryRequest categoryRequest);
    PageResponse<List<CategoriesDto>> getAllCategories(Integer pageNo, Integer pageSize);
    ApiResponse<CategoriesDto> getCategoryById(UUID id);
    ApiResponse<CategoriesDto> deleteCategoryById(UUID id);
    ApiResponse<CategoriesDto> updateCategoryById(UUID id, CategoryRequest categoryRequest);
    PageResponse<List<CategoriesDto>> searchCategoryByName(String name, Integer pageNo, Integer pageSize);

}
