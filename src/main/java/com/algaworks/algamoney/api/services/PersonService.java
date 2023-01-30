package com.algaworks.algamoney.api.services;

import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person pessoa) {
        Person personSave = findPersonById(id);

        BeanUtils.copyProperties(pessoa, personSave, "id");
        return personRepository.save(personSave);
    }

    public void updatePropertyStatus(Long id, Boolean status) {
        Person personSave = findPersonById(id);
        personSave.setStatus(status);
        personRepository.save(personSave);
    }

    private Person findPersonById(Long id) {
        Person personSave = personRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return personSave;
    }
}
