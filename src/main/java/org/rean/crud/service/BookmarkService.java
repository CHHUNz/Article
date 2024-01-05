package org.rean.crud.service;

import org.rean.crud.model.dto.BookMarkDto;
import org.rean.crud.model.request.BookmarkRequest;
import org.rean.crud.model.response.ApiResponse;

import java.util.UUID;

public interface BookmarkService {
    ApiResponse<BookMarkDto> CreateBookmark(UUID id, BookmarkRequest bookmarkRequest);
}
