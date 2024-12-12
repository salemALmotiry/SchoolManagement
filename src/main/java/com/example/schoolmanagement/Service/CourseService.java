package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.ApiResponse.ApiException;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Repository.CourseRepository;
import com.example.schoolmanagement.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseService {


    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;


    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Integer teacherId, Course course) {
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher == null){
            throw new ApiException("Teacher not found");
        }
        course.setTeacher(teacher);
        teacher.getCourses().add(course);
        teacherRepository.save(teacher);
        courseRepository.save(course);

    }

    public void update(Integer courseId, Course course){
        Course oldCourse = courseRepository.findCourseById(courseId);
        Teacher teacher = teacherRepository.findTeacherById(course.getId());
        if (oldCourse == null){
            throw new ApiException("Course not found");
        }
        if (teacher == null) {
            throw new ApiException("Teacher not found");
        }

        oldCourse.setName(course.getName());
        oldCourse.setTeacher(teacher);
        courseRepository.save(oldCourse);

    }

    public void delete(Integer courseId){
        Course course = courseRepository.findCourseById(courseId);
        if (course == null){
            throw new ApiException("Course not found");
        }
        Teacher teacher = teacherRepository.findTeacherById(course.getId());

        courseRepository.delete(course);
        if (teacher != null){
            teacher.getCourses().remove(course);
            teacherRepository.save(teacher);

        }



        if (courseRepository.findCourseById(courseId) != null){
            throw new ApiException("Course not deleted");
        }




    }

    public String getTeacherNameForCourse(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found");
        }
        Teacher teacher = course.getTeacher();
        if (teacher == null) {
            throw new ApiException("No teacher assigned to this course");
        }
        return teacher.getName();
    }

}
