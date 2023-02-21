package com.algaworks.algamoney.api.repositories;

import com.algaworks.algamoney.api.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    public Optional<AppUser> findByEmail(String email);
}
