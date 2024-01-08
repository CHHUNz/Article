package org.rean.crud.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Comment;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private UUID id;
    private String title;
    private String description;
    private Boolean published;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto users;
    private List<CommentDto> comments;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoriesDto> categories;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BookMarkDto> bookMark;

    public ArticleDto(UUID id, String title, String description, Boolean published, UserDto users, List<CategoriesDto> categories, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
        this.users = users;
        this.comments = comments;
        this.categories = categories;
    }
}
