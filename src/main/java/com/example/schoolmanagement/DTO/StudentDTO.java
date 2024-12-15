package com.example.schoolmanagement.DTO;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {

    private String name;
    private Integer age;
    private String major;

    private List<CourseDTO> courses;



}
