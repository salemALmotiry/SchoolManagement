package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.ApiResponse.ApiResponse;
import com.example.schoolmanagement.Service.TeacherService;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.DTO.TeacherInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;



    @GetMapping("/get")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTeacher(@RequestBody @Valid Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateTeacher(@RequestBody @Valid Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted successfully"));
    }

    @GetMapping("/get-teacher/{id}")
    public ResponseEntity<TeacherInfoDTO> getTeacherById(@PathVariable Integer id) {
        TeacherInfoDTO teacherInfoDTO = teacherService.getTeacherById(id);
        return ResponseEntity.status(200).body(teacherInfoDTO);
    }
}