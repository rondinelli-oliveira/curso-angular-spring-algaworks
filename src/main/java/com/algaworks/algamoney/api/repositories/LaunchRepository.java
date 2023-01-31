package com.algaworks.algamoney.api.repositories;

import com.algaworks.algamoney.api.models.Launch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchRepository extends JpaRepository<Launch, Long> {
}
