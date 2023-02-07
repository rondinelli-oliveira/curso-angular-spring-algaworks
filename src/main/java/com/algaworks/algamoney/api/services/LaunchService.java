package com.algaworks.algamoney.api.services;

import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.repositories.LaunchRepository;
import com.algaworks.algamoney.api.services.exceptions.NonExistentPersonOrInactiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaunchService {

    @Autowired
    private PersonService personService;

    @Autowired
    private LaunchRepository launchRepository;

    public Launch save(Launch launch) {
        Person person = personService.findPersonById(launch.getPerson().getId());
        if (person == null || person.isInactive()) {
            throw new NonExistentPersonOrInactiveException();
        }
        return launchRepository.save(launch);
    }
}
