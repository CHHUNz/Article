package org.rean.crud.service;

import org.rean.crud.model.dto.CommentDto;
import org.rean.crud.model.request.CommentRequest;
import org.rean.crud.model.response.ApiResponse;

import java.util.UUID;

public interface CommentService {
    ApiResponse<CommentDto> createComment(UUID articleId, CommentRequest commentRequest);
}
