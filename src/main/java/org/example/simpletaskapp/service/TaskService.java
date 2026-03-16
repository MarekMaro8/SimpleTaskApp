package org.example.simpletaskapp.service;

import org.example.simpletaskapp.dto.Task.TaskCreationDto;
import org.example.simpletaskapp.dto.Task.TaskDto;
import org.example.simpletaskapp.dto.Task.TaskMapper;
import org.example.simpletaskapp.model.Task;
import org.example.simpletaskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    public TaskDto createTask(TaskCreationDto taskCreationDto) {
        Task newTask = taskMapper.toEnitity(taskCreationDto);
        Task savedTask = taskRepository.save(newTask);
        return taskMapper.toDto(savedTask);
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .toList();

    }

    public TaskDto getTaskById(Long id) {
       return taskRepository.findById(id)
               .map(taskMapper::toDto)
               .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    @Transactional
    public TaskDto updateTask(Long id, TaskCreationDto creationDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(creationDto.title());
        task.setDescription(creationDto.description());
        task.setStatus(creationDto.status());

        return taskMapper.toDto(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
         taskRepository.delete(task);
    }
}
