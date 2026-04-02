package com.innovatech.dto;

import com.innovatech.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskWithProjectDTO {
    private Task task;
    private ProjectDTO project;
    
}
