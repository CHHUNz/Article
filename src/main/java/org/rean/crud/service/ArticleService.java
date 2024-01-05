package org.rean.crud.service;

import io.swagger.v3.core.jackson.ApiResponsesSerializer;
import org.rean.crud.model.Article;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.request.ArticleRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    ApiResponse<ArticleDto> createArticle(ArticleRequest articleRequest);
    PageResponse<List<ArticleDto>> getAllArticle(Integer pageNo, Integer pageSize);
    ApiResponse<ArticleDto> updateArticleById(UUID id, ArticleRequest articleRequest);
    ApiResponse<ArticleDto> deleteArticle(UUID id);
}
