package com.algaworks.algamoney.api.repositories.person;

import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.repositories.filters.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonRepositoryQuery {

    public Page<Person> filter(PersonFilter personFilter, Pageable pageable);

}
