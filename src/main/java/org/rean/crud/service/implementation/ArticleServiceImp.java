package org.rean.crud.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.exception.NullExceptionClass;
import org.rean.crud.model.Article;
import org.rean.crud.model.Categories;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.request.ArticleRequest;
import org.rean.crud.model.request.CategoryRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;
import org.rean.crud.repository.ArticleRepository;
import org.rean.crud.repository.CategoryRepository;
import org.rean.crud.repository.UserRepository;
import org.rean.crud.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public PageResponse<List<ArticleDto>> getAllArticle(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ArticleDto> pages = articleRepository.findAll(pageable).map(Article::toDto);
        return PageResponse.<List<ArticleDto>>builder()
                .message("get all article successfully")
                .status(HttpStatus.OK)
                .payload(pages.getContent())
                .page(pageNo)
                .size(pageSize)
                .totalElement(pages.getTotalElements())
                .totalPage(pages.getTotalPages())
                .build();
    }

    @Override
    public ApiResponse<ArticleDto> updateArticleById(UUID id, ArticleRequest articleRequest) {
        if (articleRequest.getTitle().isBlank()){
            throw new NullExceptionClass("category name can not be blank or empty", "Article");
        }
        Article article = articleRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("article not found"));
        Users users = userRepository.findById(articleRequest.getUserId()).orElseThrow(()-> new NotFoundExceptionClass("User not found"));
        List<Categories> categories = new ArrayList<>();
        for (CategoryRequest categoryRequest : articleRequest.getCategories()){
            if (categoryRequest.getName() == null || categoryRequest.getName().isBlank()){
                throw new NullExceptionClass("category name can not be blank or empty", "Article");
            }
            Categories category = categoryRepository.findByName(categoryRequest.getName()).orElseThrow(()-> new NotFoundExceptionClass("category is not found"));
            categories.add(category);
        }
        return ApiResponse.<ArticleDto>builder()
                .message("Update successfully")
                .status(HttpStatus.OK)
                .payload(articleRepository.save(articleRequest.toEntity(article.getId(), users, categories)).toDto())
                .build();
    }

    @Override
    public ApiResponse<ArticleDto> deleteArticle(UUID id) {
        Article article = articleRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass(" Article not found "));
        articleRepository.deleteById(article.getId());
        return ApiResponse.<ArticleDto>builder()
                .message("delete article successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
    }


}
