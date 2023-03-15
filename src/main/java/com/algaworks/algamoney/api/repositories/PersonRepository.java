package com.algaworks.algamoney.api.repositories;

import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.repositories.person.PersonRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuery {

}
