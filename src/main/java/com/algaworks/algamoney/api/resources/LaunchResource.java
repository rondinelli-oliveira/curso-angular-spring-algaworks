package com.algaworks.algamoney.api.resources;

import com.algaworks.algamoney.api.event.ResouceCreatedEvent;
import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.repositories.LaunchRepository;
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
@RequestMapping("api/v1")
public class LaunchResource {

    @Autowired
    LaunchRepository launchRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/launchies")

    public List<Launch> findAll() {
        return launchRepository.findAll();
    }

    @GetMapping("/launchies/{id}")
    public ResponseEntity<Launch> findById(@PathVariable Long id) {
        Optional<Launch> launch = launchRepository.findById(id);
        return launch.isPresent() ? ResponseEntity.ok(launch.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/launchies")
    public ResponseEntity<Launch> save(@Valid @RequestBody Launch launch, HttpServletResponse response) {
        Launch launchSave = launchRepository.save(launch);
        publisher.publishEvent(new ResouceCreatedEvent(this, response, launchSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(launchSave);
    }
}
