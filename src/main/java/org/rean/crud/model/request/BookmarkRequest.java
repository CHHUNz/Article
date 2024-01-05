package org.rean.crud.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Article;
import org.rean.crud.model.BookMark;
import org.rean.crud.model.Users;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkRequest {
    private UUID article_id;
    public BookMark toEntity(Article article, Users users){
        return new BookMark(null, article, users);
    }
}
