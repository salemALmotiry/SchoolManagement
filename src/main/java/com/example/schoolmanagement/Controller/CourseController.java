package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.ApiResponse.ApiResponse;
import com.example.schoolmanagement.DTO.StudentDTO;
import com.example.schoolmanagement.Service.CourseService;
import com.example.schoolmanagement.Model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(200).body(courses);
    }

    @PostMapping("/add/{teacherId}")
    public ResponseEntity<ApiResponse> addCourse(@PathVariable Integer teacherId, @RequestBody @Valid Course course) {
        courseService.addCourse(teacherId, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<ApiResponse> updateCourse(@PathVariable Integer courseId, @RequestBody @Valid Course course) {
        courseService.update(courseId, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Integer courseId) {
        courseService.delete(courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
    }

    @GetMapping("/get-teacher-name/{courseId}")
    public ResponseEntity<String> getTeacherNameForCourse(@PathVariable Integer courseId) {
        String teacherName = courseService.getTeacherNameForCourse(courseId);
        return ResponseEntity.status(200).body(teacherName);
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity getAllStudentsForCourse(@PathVariable Integer courseId) {
        List<StudentDTO> students = courseService.getAllStudents(courseId);
        return ResponseEntity.ok(students);
    }
}