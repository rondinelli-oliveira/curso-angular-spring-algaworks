package com.algaworks.algamoney.api.repositories;

import com.algaworks.algamoney.api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
