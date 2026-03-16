package org.example.simpletaskapp.controller;


import jakarta.validation.Valid;
import org.example.simpletaskapp.dto.Task.TaskCreationDto;
import org.example.simpletaskapp.dto.Task.TaskDto;
import org.example.simpletaskapp.dto.Task.TaskMapper;
import org.example.simpletaskapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long id) {
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskCreationDto taskCreationDto) {
        TaskDto task = taskService.createTask(taskCreationDto);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable long id, @Valid @RequestBody TaskCreationDto taskCreationDto) {
        TaskDto updatedTask = taskService.updateTask(id, taskCreationDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
