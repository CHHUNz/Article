package org.rean.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Article;
import org.rean.crud.model.Users;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkDto {
    private UUID id;
    private Article article;
    private Users users;
    public BookMarkDto(Article article){
        this.article = article;
    }
}
