package org.rean.crud.service;

import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.dto.BookMarkDto;
import org.rean.crud.model.request.BookmarkRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;

import java.util.List;
import java.util.UUID;

public interface BookmarkService {
    ApiResponse<ArticleDto> CreateBookmark(UUID id, BookmarkRequest bookmarkRequest);
    PageResponse<List<BookMarkDto>> getAllBookMark(UUID userId, Integer pageNo, Integer pageSize);
}
