package com.algaworks.algamoney.api.repositories;

import com.algaworks.algamoney.api.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
