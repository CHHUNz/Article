package org.rean.crud.controller;

import lombok.RequiredArgsConstructor;
import org.rean.crud.model.request.ArticleRequest;
import org.rean.crud.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest articleRequest){
        return ResponseEntity.ok().body(articleService.createArticle(articleRequest));
    }
}
