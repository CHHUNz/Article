package org.rean.crud.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.exception.NullExceptionClass;
import org.rean.crud.model.Categories;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.request.ArticleRequest;
import org.rean.crud.model.request.CategoryRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.repository.ArticleRepository;
import org.rean.crud.repository.CategoryRepository;
import org.rean.crud.repository.UserRepository;
import org.rean.crud.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<ArticleDto> createArticle(ArticleRequest articleRequest) {
        if (articleRequest.getTitle().isBlank()) {
            throw new NullExceptionClass("Tiltle can not blank", "Article");
        }
        List<Categories> categories = new ArrayList<>();
        for (CategoryRequest categoryRequest : articleRequest.getCategories()) {
            if (categoryRequest.getName() == null || categoryRequest.getName().isBlank()) {
                throw new NullExceptionClass("category name can not be blank or empty", "Article");
            }
            Categories category = categoryRepository.findByName(categoryRequest.getName()).orElseThrow(()-> new NotFoundExceptionClass("Category not found"));
            categories.add(category);
            log("Loggg : "+category.getName());
        }
        Users users = userRepository.findById(articleRequest.getUserId()).orElseThrow(()-> new NotFoundExceptionClass("User not found"));
        return ApiResponse.<ArticleDto>builder()
                .message("Create article successfully")
                .status(HttpStatus.OK)
                .payload(articleRepository.save(articleRequest.toEntity(users, categories)).toDto())
                .build();
    }
}
