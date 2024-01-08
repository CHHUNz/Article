package org.rean.crud.service.implementation;

import lombok.RequiredArgsConstructor;
import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.exception.NullExceptionClass;
import org.rean.crud.model.Article;
import org.rean.crud.model.dto.CommentDto;
import org.rean.crud.model.request.CommentRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.repository.ArticleRepository;
import org.rean.crud.repository.CommentRepository;
import org.rean.crud.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    @Override
    public ApiResponse<CommentDto> createComment(UUID articleId, CommentRequest commentRequest) {
        if (commentRequest.getComment_description().isBlank()){
            throw  new NullExceptionClass("Comment can not blank", "Comment");
        }
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new NotFoundExceptionClass("Article is not found"));
        return ApiResponse.<CommentDto>builder()
                .message("Comment Successfully")
                .status(HttpStatus.OK)
                .payload(commentRepository.save(commentRequest.toEntity(article)).toDto())
                .build();
    }
}
