package org.rean.crud.controller;

import lombok.RequiredArgsConstructor;
import org.rean.crud.model.dto.CategoriesDto;
import org.rean.crud.model.request.CategoryRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok().body(categoryService.createCategory(categoryRequest));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
    ){
        return ResponseEntity.ok().body(categoryService.getAllCategories(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(UUID id){
        return ResponseEntity.ok().body(categoryService.deleteCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(UUID id, CategoryRequest categoryRequest){
        return ResponseEntity.ok().body(categoryService.updateCategoryById(id, categoryRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCategoryByName(String name,
                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "5") Integer pageSize){
        return ResponseEntity.ok().body(categoryService.searchCategoryByName(name, pageNo, pageSize));
    }
}
