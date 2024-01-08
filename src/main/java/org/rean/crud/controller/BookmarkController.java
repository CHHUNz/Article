package org.rean.crud.controller;

import lombok.RequiredArgsConstructor;
import org.rean.crud.model.request.BookmarkRequest;
import org.rean.crud.model.response.ApiResponse;
import org.rean.crud.service.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/user/{id}")
    public ResponseEntity<?> createBookmark(@PathVariable("id") UUID id, @RequestBody BookmarkRequest bookmarkRequest){
        return ResponseEntity.ok().body(bookmarkService.CreateBookmark(id, bookmarkRequest));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllBookmarkByUserId(@PathVariable("id") UUID id,
                                                    @RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "5") Integer pageSize){
        return ResponseEntity.ok().body(bookmarkService.getAllBookMark(id, pageNo, pageSize));

    }
}
