package com.algaworks.algamoney.api.resources;

import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.repositories.LaunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class LaunchResource {

    @Autowired
    LaunchRepository launchRepository;

    @GetMapping("/launchies")

    public List<Launch> findAll() {
        return launchRepository.findAll();
    }

    @GetMapping("/launchies/{id}")
    public ResponseEntity<Launch> findById(@PathVariable Long id) {
        Optional<Launch> launch = launchRepository.findById(id);
        return launch.isPresent() ? ResponseEntity.ok(launch.get()) : ResponseEntity.notFound().build();
    }
}
