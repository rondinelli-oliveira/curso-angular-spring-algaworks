package com.algaworks.algamoney.api.resources;

import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.repositories.PersonRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @PostMapping("/people")
    public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response) {
        Person personSave = personRepository.save(person);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(personSave.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(personSave);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
    }
}
