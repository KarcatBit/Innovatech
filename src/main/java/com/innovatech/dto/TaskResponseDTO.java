package com.innovatech.dto;

import lombok.Data;
import com.innovatech.model.Task;

@Data
public class TaskResponseDTO {
    
    private Task task;

    private Object project;
}
