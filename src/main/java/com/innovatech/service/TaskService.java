package com.innovatech.service;

import org.springframework.stereotype.Service;
import com.innovatech.model.Task;
import com.innovatech.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

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
