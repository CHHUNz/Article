package org.rean.crud.controller;

import lombok.RequiredArgsConstructor;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.request.ArticleRequest;
import org.rean.crud.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/article")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest articleRequest){
        return ResponseEntity.ok().body(articleService.createArticle(articleRequest));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllArticle(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize){
        return ResponseEntity.ok().body(articleService.getAllArticle(pageNo, pageSize));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticleById(@PathVariable("id") UUID id,
                                               @RequestBody ArticleRequest articleRequest){
        return ResponseEntity.ok().body(articleService.updateArticleById(id, articleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticleById(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(articleService.deleteArticle(id));
    }


}
