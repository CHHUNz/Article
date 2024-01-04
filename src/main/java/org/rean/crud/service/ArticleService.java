package org.rean.crud.service;

import org.rean.crud.model.Article;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.request.ArticleRequest;
import org.rean.crud.model.response.ApiResponse;

public interface ArticleService {
    ApiResponse<ArticleDto> createArticle(ArticleRequest articleRequest);
}
