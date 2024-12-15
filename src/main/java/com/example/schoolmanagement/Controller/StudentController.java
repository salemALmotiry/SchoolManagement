package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.ApiResponse.ApiResponse;
import com.example.schoolmanagement.DTO.StudentDTO;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        List<StudentDTO> studentDTOList = studentService.getAll();
        return ResponseEntity .status(200).body(studentDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student) {
        studentService.add(student);
        return ResponseEntity .status(200).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestBody @Valid Student student) {
        studentService.updateStudent(student);
        return ResponseEntity .status(200).body(new ApiResponse("Student updated successfully"));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity .status(200).body(new ApiResponse("Student deleted successfully"));
    }

    @PutMapping("/change-major/{studentId}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer studentId, @PathVariable String major) {
        studentService.changeMajor(studentId, major);
        return ResponseEntity .status(200).body(new ApiResponse("Student major changed successfully"));
    }

    @PutMapping("/assign/{studentId}/{courseId}")
    public ResponseEntity assignCourseToStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.assignCourse(studentId,courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Course assigned successfully!"));

    }
}
