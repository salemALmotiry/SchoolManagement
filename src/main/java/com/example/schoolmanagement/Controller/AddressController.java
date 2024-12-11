package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.ApiResponse.ApiResponse;
import com.example.schoolmanagement.Service.AddressService;
import com.example.schoolmanagement.Model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAddress(@RequestBody @Valid Address address) {
        addressService.addAddress(address);
        return ResponseEntity.status(200).body(new ApiResponse("Address added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateAddress(@RequestBody @Valid Address address) {
        addressService.updateAddress(address);
        return ResponseEntity.status(200).body(new ApiResponse("Address updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body(new ApiResponse("Address deleted successfully"));
    }


}