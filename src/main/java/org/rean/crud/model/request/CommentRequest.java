package org.rean.crud.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Article;
import org.rean.crud.model.Comment;
import org.rean.crud.model.dto.CommentDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String comment_description;
    public Comment toEntity(Article article){
        return new Comment(null, this.comment_description, article);
    }
}
