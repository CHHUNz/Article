package org.rean.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.dto.BookMarkDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_bookmark")
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "bookmark_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public BookMarkDto toDto(){
        return new BookMarkDto(this.article);
    }

}
