package com.example.schoolmanagement.Service;


import com.example.schoolmanagement.ApiResponse.ApiException;
import com.example.schoolmanagement.DTO.CourseDTO;
import com.example.schoolmanagement.DTO.StudentDTO;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Repository.CourseRepository;
import com.example.schoolmanagement.Repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setAge(student.getAge());
            studentDTO.setAge(student.getAge());

            for (Course course : student.getCourses()) {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setName(course.getName());
                courseDTOS.add(courseDTO);
            }

            studentDTO.setCourses(courseDTOS);
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }

    public void add(Student student) {
        Student student1 = studentRepository.findStudentById(student.getId());
        if (student1 != null) {
            throw new ApiException("Student already exist");
        }

        studentRepository.save(student);
    }


    public void updateStudent(Student student){
        Student oldStudent = studentRepository.findStudentById(student.getId());
        if(oldStudent == null){
            throw new ApiException("student not found");
        }

        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        oldStudent.setCourses(student.getCourses());
        studentRepository.save(oldStudent);
    }


    public void deleteStudent(Integer studentId){
        Student student = studentRepository.findStudentById(studentId);
        if(student == null){
            throw new ApiException("student not found");
        }

        studentRepository.delete(student);
    }

    public void changeMajor(Integer studentId, String major){
        Student student = studentRepository.findStudentById(studentId);
        if(student == null){
            throw new ApiException("student not found");
        }
        student.setMajor(major);
        student.setCourses(null);
        studentRepository.save(student);
    }

    public void assignCourse(Integer courseId,Integer studentId){
        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);
        if (student == null){
            throw new RuntimeException("student not found");
        }
        if (course == null){
            throw new RuntimeException("course not found");
        }
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);
    }

}
