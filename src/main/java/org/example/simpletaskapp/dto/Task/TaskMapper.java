package org.example.simpletaskapp.dto.Task;

import org.example.simpletaskapp.model.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {

    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getStatus()
        );
    }

    public Task toEnitity(TaskCreationDto taskCreationDto) {

        Task task = new Task();
        task.setTitle(taskCreationDto.title());
        task.setDescription(taskCreationDto.description());
        task.setCreatedAt(LocalDate.now());
        task.setStatus(Task.Status.NEW);

        return task;
    }

}
