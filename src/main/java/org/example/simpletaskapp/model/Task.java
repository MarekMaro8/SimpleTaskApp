package org.example.simpletaskapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
@Entity
@Table(name = "tasks")
public class Task {


@Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private LocalDate createdAt;
    private Status status;

    public Task() {
    }


    public Task(String title, String description, LocalDate createdAt, Status status) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
    }

    public enum Status {
        NEW,
        IN_PROGRESS,
        DONE
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

}
