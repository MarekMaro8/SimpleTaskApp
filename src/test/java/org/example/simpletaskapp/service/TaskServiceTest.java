package org.example.simpletaskapp.service;

import org.example.simpletaskapp.dto.Task.TaskCreationDto;
import org.example.simpletaskapp.dto.Task.TaskDto;
import org.example.simpletaskapp.dto.Task.TaskMapper;
import org.example.simpletaskapp.exception.TaskNotFoundException;
import org.example.simpletaskapp.model.Task;
import org.example.simpletaskapp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void getAllTasks_ShouldReturnListOfTaskDto() {
        Task task = new Task("Tytuł", "Opis", LocalDate.now(), Task.Status.NEW);
        TaskDto taskDto = new TaskDto(1L, "Tytuł", "Opis", LocalDate.now(), Task.Status.NEW);

        when(taskRepository.findAll()).thenReturn(List.of(task));
        when(taskMapper.toDto(task)).thenReturn(taskDto);

        List<TaskDto> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Tytuł", result.get(0).title());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void getTaskById_WhenTaskExists_ShouldReturnTaskDto() {
        Long taskId = 1L;
        Task task = new Task();
        TaskDto taskDto = new TaskDto(taskId, "Tytuł", null, LocalDate.now(), Task.Status.NEW);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskMapper.toDto(task)).thenReturn(taskDto);

        TaskDto result = taskService.getTaskById(taskId);

        assertNotNull(result);
        assertEquals(taskId, result.id());
    }

    @Test
    void getTaskById_WhenTaskDoesNotExist_ShouldThrowException() {
        Long taskId = 99L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        TaskNotFoundException exception = assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
        assertEquals("Task not found with id: 99", exception.getMessage());
    }

    @Test
    void createTask_ShouldReturnCreatedTaskDto() {
        TaskCreationDto creationDto = new TaskCreationDto("Tytuł", "Opis", Task.Status.NEW);
        Task taskToSave = new Task();
        Task savedTask = new Task();
        TaskDto expectedDto = new TaskDto(1L, "Tytuł", "Opis", LocalDate.now(), Task.Status.NEW);

        when(taskMapper.toEntity(creationDto)).thenReturn(taskToSave);
        when(taskRepository.save(taskToSave)).thenReturn(savedTask);
        when(taskMapper.toDto(savedTask)).thenReturn(expectedDto);

        TaskDto result = taskService.createTask(creationDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        verify(taskRepository).save(taskToSave);
    }

    @Test
    void updateTask_ShouldUpdateFieldsAndReturnDto() {
        Long taskId = 1L;
        TaskCreationDto updateDto = new TaskCreationDto("Nowy tytuł", "Nowy opis", Task.Status.IN_PROGRESS);
        Task existingTask = new Task("Stary tytuł", "Stary opis", LocalDate.now(), Task.Status.NEW);
        TaskDto expectedDto = new TaskDto(taskId, "Nowy tytuł", "Nowy opis", LocalDate.now(), Task.Status.IN_PROGRESS);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(existingTask);
        when(taskMapper.toDto(existingTask)).thenReturn(expectedDto);

        TaskDto result = taskService.updateTask(taskId, updateDto);

        assertEquals("Nowy tytuł", existingTask.getTitle());
        assertEquals(Task.Status.IN_PROGRESS, existingTask.getStatus());
        assertEquals(expectedDto, result);
        verify(taskRepository).save(existingTask);
    }

    @Test
    void deleteTask_ShouldCallRepositoryDelete() {
        Long taskId = 1L;
        Task existingTask = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).delete(existingTask);
    }
}