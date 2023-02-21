package com.algaworks.algamoney.api.repositories.projection;

import com.algaworks.algamoney.api.models.enums.TypeLaunch;

import java.time.LocalDate;

public class LaunchSummary {

    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate payday;
    private Double value;
    private TypeLaunch type;
    private String category;
    private String person;

    public LaunchSummary(Long id, String description, LocalDate dueDate, LocalDate payday, Double value, TypeLaunch type, String category, String person) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.payday = payday;
        this.value = value;
        this.type = type;
        this.category = category;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TypeLaunch getType() {
        return type;
    }

    public void setType(TypeLaunch type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
