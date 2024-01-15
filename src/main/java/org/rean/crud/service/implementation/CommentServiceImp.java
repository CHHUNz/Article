package org.rean.crud.service.implementation;

import lombok.RequiredArgsConstructor;
import org.rean.crud.exception.NotFoundExceptionClass;
import org.rean.crud.exception.NullExceptionClass;
import org.rean.crud.model.Article;
import org.rean.crud.model.Comment;
import org.rean.crud.model.dto.CommentDto;
import org.rean.crud.model.request.CommentRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.model.response.PageResponse;
import org.rean.crud.repository.ArticleRepository;
import org.rean.crud.repository.CommentRepository;
import org.rean.crud.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

//    @Override
//    public ApiResponse<List<CommentDto>> getAllCommentInArticle(UUID id) {
//        Article article = articleRepository.findById(id).orElseThrow(()-> new NotFoundExceptionClass("Article not found"));
//        return ApiResponse.<List<CommentDto>>builder()
//                .message("Get all comment in article Id : " + article.getId())
//                .status(HttpStatus.OK)
//                .payload(commentRepository.findAllByArticleId(article.getId()).stream().map(Comment::toDto).collect(Collectors.toList()))
//                .build();
//    }


}
