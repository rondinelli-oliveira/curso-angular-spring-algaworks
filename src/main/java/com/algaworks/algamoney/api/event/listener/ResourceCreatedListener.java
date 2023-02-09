package com.algaworks.algamoney.api.event.listener;

import com.algaworks.algamoney.api.event.ResouceCreatedEvent;
//import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResouceCreatedEvent> {

    @Override
    public void onApplicationEvent(ResouceCreatedEvent resourceCreatedEvent) {
        HttpServletResponse response = resourceCreatedEvent.getResponse();
        Long id = resourceCreatedEvent.getId();

        addHeaderLocation(response, id);
    }

    private void addHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
