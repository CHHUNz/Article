package org.rean.crud.repository;

import org.rean.crud.model.Categories;
import org.rean.crud.model.dto.CategoriesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, UUID> {
    Optional<Categories> findByName(String name);
    Page<Categories> findCategoriesByNameContainingIgnoreCase(Pageable pageable, String name);
}
