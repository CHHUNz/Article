package org.rean.crud.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.Article;
import org.rean.crud.model.Categories;
import org.rean.crud.model.Users;
import org.rean.crud.model.dto.ArticleDto;
import org.rean.crud.model.dto.BookMarkDto;
import org.rean.crud.model.dto.CategoriesDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String description;
    private Boolean published = false;
    private Set<CategoryRequest> categories;
    private UUID userId;

    public Article toEntity(Users user, List<Categories> categories){
        return new Article(null, this.title, this.description, this.published, user, categories);
    }
    public Article toEntity(UUID id, Users user, List<Categories> categories){
        return new Article(id, this.title, this.description, this.published, user, categories);
    }

}
