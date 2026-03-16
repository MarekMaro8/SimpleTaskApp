package org.example.simpletaskapp.dto.Task;

import org.example.simpletaskapp.model.Task;

import java.time.LocalDate;

public record TaskDto(
        Long id,
        String title,
        String description,
        LocalDate createdAt,
        Task.Status status
) {
}
