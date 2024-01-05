package org.rean.crud.repository;

import org.rean.crud.model.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookmarkRepository extends JpaRepository<BookMark, UUID> {
}
