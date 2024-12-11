package com.example.schoolmanagement.Repository;

import com.example.schoolmanagement.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findAddressById(Integer id);
    Boolean deleteAddressById(Integer id);
}
