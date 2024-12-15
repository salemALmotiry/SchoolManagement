package com.example.schoolmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfoDTO {
    private String name;
    private Integer age;
    private String email;
    private Double salary;

    private String area;
    private String street;
    private Integer buildingNumber;

    private List<CourseDTO> courses;

}