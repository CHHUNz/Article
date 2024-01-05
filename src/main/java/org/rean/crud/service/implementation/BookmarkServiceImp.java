package org.rean.crud.service.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.model.Article;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.BookMarkDto;
import org.rean.crud.model.request.BookmarkRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.repository.ArticleRepository;
import org.rean.crud.repository.BookmarkRepository;
import org.rean.crud.repository.UserRepository;
import org.rean.crud.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImp implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    @Override
    public ApiResponse<BookMarkDto> CreateBookmark(UUID id, BookmarkRequest bookmarkRequest) {
        Users users = userRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("User not found"));
        Article article = articleRepository.findById(bookmarkRequest.getArticle_id()).orElseThrow(()-> new NotFoundExceptionClass("Article is not found"));
        return ApiResponse.<BookMarkDto>builder()
                .message("Bookmark successfully")
                .status(HttpStatus.OK)
                .payload(bookmarkRepository.save(bookmarkRequest.toEntity(article, users)).getArticle().toDto())
                .build();
    }
}
