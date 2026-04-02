package com.innovatech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_status")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTaskStatus;

    @Column(name = "status", nullable = false)
    private String status;
    
}
