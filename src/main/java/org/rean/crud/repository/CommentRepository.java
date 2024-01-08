package org.rean.crud.repository;

import org.rean.crud.model.Comment;
import org.rean.crud.model.dto.CommentDto;
import org.rean.crud.model.request.CommentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByArticleId(UUID id);
}
