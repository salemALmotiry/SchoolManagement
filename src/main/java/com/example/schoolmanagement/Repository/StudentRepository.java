package com.example.schoolmanagement.Repository;

import com.example.schoolmanagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);
}
