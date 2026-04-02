package com.innovatech.service;

import org.springframework.stereotype.Service;

import com.innovatech.model.Task;
import com.innovatech.repository.TaskRepository;
import com.innovatech.dto.ProjectDTO;
import com.innovatech.dto.TaskWithProjectDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TaskWithProjectDTO getTaskComplete(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        
        if (task != null && task.getProjectId() != null) {
            String url = "http://localhost:9090/endpoint/proyect/" + task.getProjectId();
            ProjectDTO project = restTemplate.getForObject(url, ProjectDTO.class);

            return new TaskWithProjectDTO(task, project);
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByDateRange(LocalDate start, LocalDate end) {
        return taskRepository.findByDateCreatedBetween(start, end);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
}
