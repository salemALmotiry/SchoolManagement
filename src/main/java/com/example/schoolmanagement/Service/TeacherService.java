package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.ApiResponse.ApiException;
import com.example.schoolmanagement.DTO.TeacherInfoDTO;
import com.example.schoolmanagement.Model.Address;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Repository.AddressRepository;
import com.example.schoolmanagement.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        Teacher teacher1 = teacherRepository.findTeacherById(teacher.getId());
        if(teacher1 != null){
            throw new ApiException("Teacher already exists");
        }
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Teacher teacher){
        Teacher oldTeacher = teacherRepository.findTeacherById(teacher.getId());
        if(oldTeacher == null){
            throw new ApiException("Teacher not found");
        }

        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setSalary(teacher.getSalary());
        oldTeacher.setEmail(teacher.getEmail());
        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher == null){
            throw new ApiException("Teacher not found");
        }
        Address address = addressRepository.findAddressById(id);
        if(address != null){
            address.setId(null);
            addressRepository.delete(address);
            
        }
        teacherRepository.deleteTeacherById(id);

    }

    public TeacherInfoDTO getTeacherById(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher == null){
            throw new ApiException("Teacher not found");
        }
        Address address = addressRepository.findAddressById(id);
        if(address == null){
            throw new ApiException("Address not found");
        }

        TeacherInfoDTO teacherInfoDTO = new TeacherInfoDTO();
        teacherInfoDTO.setId(teacher.getId());
        teacherInfoDTO.setName(teacher.getName());
        teacherInfoDTO.setEmail(teacher.getEmail());
        teacherInfoDTO.setStreet(address.getStreet());
        teacherInfoDTO.setArea( address.getArea());
        teacherInfoDTO.setBuildingNumber(address.getBuildingNumber());
        teacherInfoDTO.setCourses(teacher.getCourses());

        return teacherInfoDTO;
    }

}
