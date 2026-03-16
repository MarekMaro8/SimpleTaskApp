package org.example.simpletaskapp.dto.Task;

import jakarta.validation.constraints.NotBlank;
import org.example.simpletaskapp.model.Task;

public record TaskCreationDto(
        @NotBlank(message = "Title is needed")
        String title,

        @NotBlank(message = "Description is needed")
        String description,

        Task.Status status
) {
}
