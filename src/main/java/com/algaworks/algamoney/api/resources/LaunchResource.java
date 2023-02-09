package com.algaworks.algamoney.api.resources;

import com.algaworks.algamoney.api.event.ResouceCreatedEvent;
import com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler;
import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.repositories.LaunchRepository;
import com.algaworks.algamoney.api.repositories.filters.LaunchFilter;
import com.algaworks.algamoney.api.services.LaunchService;
import com.algaworks.algamoney.api.services.exceptions.NonExistentPersonOrInactiveException;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class LaunchResource {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private LaunchService launchService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/launchies")
    public Page<Launch> search(LaunchFilter launchFilter, Pageable pageable) {
        return launchRepository.filter(launchFilter, pageable);
    }

    @GetMapping("/launchies/{id}")
    public ResponseEntity<Launch> findById(@PathVariable Long id) {
        Optional<Launch> launch = launchRepository.findById(id);
        return launch.isPresent() ? ResponseEntity.ok(launch.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/launchies")
    public ResponseEntity<Launch> save(@Valid @RequestBody Launch launch, HttpServletResponse response) {
        Launch launchSave = launchService.save(launch);
        publisher.publishEvent(new ResouceCreatedEvent(this, response, launchSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(launchSave);
    }

    @ExceptionHandler({NonExistentPersonOrInactiveException.class})
    public ResponseEntity<Object> handleNonExistentPersonOrInactiveException(NonExistentPersonOrInactiveException ex) {
        String userMessage = messageSource.getMessage("person.non-existent-or-inactive", null, LocaleContextHolder.getLocale());
        String developmentMessage = ex.toString();
        List<AlgamoneyExceptionHandler.Error> errorList = Arrays.asList(new AlgamoneyExceptionHandler.Error(userMessage, developmentMessage));
        return ResponseEntity.badRequest().body(errorList);
    }

    @DeleteMapping("/launchies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        launchRepository.deleteById(id);
    }
}
