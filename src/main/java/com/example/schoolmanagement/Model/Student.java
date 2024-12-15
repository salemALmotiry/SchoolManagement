package com.example.schoolmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name is required")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @Positive(message = "Age must be a positive number")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Major is required")
    @Column(columnDefinition = "varchar(50) not null")

    private String major;


    @ManyToMany
    private Set<Course> courses;

}
