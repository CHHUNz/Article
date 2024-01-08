package org.rean.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rean.crud.model.dto.CommentDto;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id")
    private UUID commentId;

    @Column(name = "comment_caption")
    private String comment_caption;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article articles;

    public CommentDto toDto(){
        return new CommentDto(this.commentId, this.comment_caption);
    }
}
