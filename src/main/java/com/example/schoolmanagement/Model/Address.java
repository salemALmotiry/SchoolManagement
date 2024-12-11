package com.example.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Area is required")
    @Size(min = 2, max = 100, message = "Area must be between 2 and 100 characters")
    private String area;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Street is required")
    @Size(min = 2, max = 100, message = "Street must be between 2 and 100 characters")
    private String street;

    @Column(nullable = false)
    @NotNull(message = "Building number is required")
    @Min(value = 1, message = "Building number must be at least 1")
    private Integer buildingNumber;

    @OneToOne(mappedBy = "address")
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}