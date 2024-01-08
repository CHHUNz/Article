package org.rean.crud.service.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.model.Article;
import org.rean.crud.model.BookMark;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.dto.BookMarkDto;
import org.rean.crud.model.request.BookmarkRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;
import org.rean.crud.repository.ArticleRepository;
import org.rean.crud.repository.BookmarkRepository;
import org.rean.crud.repository.UserRepository;
import org.rean.crud.service.BookmarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImp implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    @Override
    public ApiResponse<ArticleDto> CreateBookmark(UUID id, BookmarkRequest bookmarkRequest) {
        Users users = userRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("User not found"));
        Article article = articleRepository.findById(bookmarkRequest.getArticle_id()).orElseThrow(()-> new NotFoundExceptionClass("Article is not found"));
        return ApiResponse.<ArticleDto>builder()
                .message("Bookmark successfully")
                .status(HttpStatus.OK)
                .payload(bookmarkRepository.save(bookmarkRequest.toEntity(article, users)).getArticle().toDto())
                .build();
    }

    @Override
    public PageResponse<List<BookMarkDto>> getAllBookMark(UUID userId, Integer pageNo, Integer pageSize) {
        Users users = userRepository.findById(userId).orElseThrow(()-> new NotFoundExceptionClass("User is not found"));
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<BookMarkDto> page = bookmarkRepository.findAllUserById(pageable, users.getId()).map(BookMark::toDto);
        return PageResponse.<List<BookMarkDto>>builder()
                .message("Get Bookmark successfully")
                .status(HttpStatus.OK)
                .payload(page.getContent())
                .page(pageNo)
                .size(pageSize)
                .totalElement(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .build();
    }
}
