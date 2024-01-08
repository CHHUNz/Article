package org.rean.crud.service;

import org.rean.crud.model.dto.CommentDto;
import org.rean.crud.model.request.CommentRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    ApiResponse<CommentDto> createComment(UUID articleId, CommentRequest commentRequest);
    ApiResponse<List<CommentDto>> getAllCommentInArticle(UUID id);
}
