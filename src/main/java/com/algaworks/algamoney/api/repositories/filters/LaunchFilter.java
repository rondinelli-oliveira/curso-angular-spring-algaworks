package com.algaworks.algamoney.api.repositories.filters;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LaunchFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateOf;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateUntil;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDateOf() {
        return expirationDateOf;
    }

    public void setExpirationDateOf(LocalDate expirationDateOf) {
        this.expirationDateOf = expirationDateOf;
    }

    public LocalDate getExpirationDateUntil() {
        return expirationDateUntil;
    }

    public void setExpirationDateUntil(LocalDate expirationDateUntil) {
        this.expirationDateUntil = expirationDateUntil;
    }
}
