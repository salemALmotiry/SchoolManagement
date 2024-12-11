package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.ApiResponse.ApiException;
import com.example.schoolmanagement.Model.Address;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Repository.AddressRepository;
import com.example.schoolmanagement.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;


    public void addAddress(Address address){
        Teacher teacher = teacherRepository.findTeacherById(address.getId());
        if(teacher == null){
            throw new ApiException("Teacher not found");
        }
        Address address1 = addressRepository.findAddressById(address.getId());
        if(address1 != null){
            throw new ApiException("Address already exists");
        }
        
        address.setTeacher(teacher);
        
        addressRepository.save(address);
    }

    public void updateAddress(Address address) {
        Address oldAddress = addressRepository.findAddressById(address.getId());
        if (oldAddress == null) {
            throw new RuntimeException("Address not found");
        }

        Teacher teacher = teacherRepository.findTeacherById(address.getTeacher().getId());
        if (teacher == null) {
            throw new RuntimeException("Teacher not found");
        }

        oldAddress.setStreet(address.getStreet());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setArea(address.getArea());
        oldAddress.setBuildingNumber(address.getBuildingNumber());
        oldAddress.setTeacher(teacher);

        addressRepository.save(oldAddress);
    }

    public void deleteAddress(Integer addressId) {
        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new ApiException("Address not found");
        }
        addressRepository.deleteAddressById(address.getId());

        if (addressRepository.findAddressById(addressId) !=null){
            throw new ApiException("Address not deleted");
        }
        
    }
}
