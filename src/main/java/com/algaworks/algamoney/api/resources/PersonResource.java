package com.algaworks.algamoney.api.resources;

import com.algaworks.algamoney.api.event.ResouceCreatedEvent;
import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.repositories.PersonRepository;
import com.algaworks.algamoney.api.services.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/people")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @PostMapping("/people")
    public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personSave = personRepository.save(person);
        publisher.publishEvent(new ResouceCreatedEvent(this, response, personSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(personSave);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/people/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        Person personSave = personService.update(id, person);
        return ResponseEntity.ok(personSave);
    }

    @PutMapping("/people/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePropertyStatus(@PathVariable Long id, @RequestBody Boolean status) {
        personService.updatePropertyStatus(id, status);
    }
}
