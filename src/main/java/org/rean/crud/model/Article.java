package org.rean.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.dto.ArticleDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "article_id")
    private UUID id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_description")
    private String description;

    @Column(name = "article_publish")
    private Boolean published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    List<Categories> categories;

    @OneToMany(mappedBy = "article")
    private List<BookMark> bookMarks;

    public Article(UUID id, String title, String description, Boolean published, Users user, List<Categories> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
        this.user = user;
        this.categories = categories;
    }

    public ArticleDto toDto(){
        return new ArticleDto(this.id, this.title, this.title, this.published, this.user.toDto(), this.categories.stream().map(Categories::toDto).collect(Collectors.toList()));
    }
}
