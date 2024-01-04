package org.rean.crud.service.implementation;

import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.exception.NullExceptionClass;
import org.rean.crud.model.Categories;
import org.rean.crud.model.dto.CategoriesDto;
import org.rean.crud.model.request.CategoryRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;
import org.rean.crud.repository.CategoryRepository;
import org.rean.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse<CategoriesDto> createCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.getName().isBlank()){
            throw new NullExceptionClass("A category name field is required!","category");
        } else {
            var payload = categoryRepository.save(categoryRequest.toEntity()).toDto();
            return ApiResponse.<CategoriesDto>builder()
                    .message("Create category successfully")
                    .status(HttpStatus.OK)
                    .payload(payload)
                    .build();
        }
    }

    @Override
    public PageResponse<List<CategoriesDto>> getAllCategories(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CategoriesDto> page = categoryRepository.findAll(pageable).map(Categories::toDto);
        return PageResponse.<List<CategoriesDto>>builder()
                .message("Get all categories successfully")
                .status(HttpStatus.OK)
                .payload(page.getContent())
                .page(pageNo)
                .size(pageSize)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }

    @Override
    public ApiResponse<CategoriesDto> getCategoryById(UUID id) {
        var paylaod = categoryRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("Category id "+id+" not found")).toDto();
        return ApiResponse.<CategoriesDto>builder()
                .message("Get category successfully")
                .status(HttpStatus.OK)
                .payload(paylaod)
                .build();
    }

    @Override
    public ApiResponse<CategoriesDto> deleteCategoryById(UUID id) {
        Categories categories = categoryRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("Id is not found"));
        categoryRepository.deleteById(categories.getId());
        return ApiResponse.<CategoriesDto>builder()
                .message("Delete category successfully")
                .payload(null)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ApiResponse<CategoriesDto> updateCategoryById(UUID id, CategoryRequest categoryRequest) {
        if (categoryRequest.getName().isBlank()){
            throw new NullExceptionClass("Category name can not blank", "category");
        } else {
            return ApiResponse.<CategoriesDto>builder()
                    .message("Update category successfully")
                    .status(HttpStatus.OK)
                    .payload(categoryRepository.save(categoryRequest.toEntity(id)).toDto())
                    .build();
        }
    }

    @Override
    public PageResponse<List<CategoriesDto>> searchCategoryByName(String name, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CategoriesDto> page = categoryRepository.findCategoriesByNameContainingIgnoreCase(pageable, name).map(Categories::toDto);
        if ((page.isEmpty())){
            throw new NotFoundExceptionClass("Category is not found");
        } else {
            return PageResponse.<List<CategoriesDto>>builder()
                    .message("successfully")
                    .status(HttpStatus.OK)
                    .payload(page.getContent())
                    .page(pageNo)
                    .size(pageSize)
                    .totalElement(page.getTotalElements())
                    .totalPage(page.getTotalPages())
                    .build();
        }
    }
}
