package org.rean.crud.controller;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.rean.crud.model.request.CommentRequest;
import org.rean.crud.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> createComment(UUID id, CommentRequest commentRequest){
        return ResponseEntity.ok().body(commentService.createComment(id, commentRequest));
    }

}
