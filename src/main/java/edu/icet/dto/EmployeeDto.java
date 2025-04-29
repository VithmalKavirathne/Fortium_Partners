package edu.icet.dto;

import icet.edu.com.util.Department;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Department department;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}
