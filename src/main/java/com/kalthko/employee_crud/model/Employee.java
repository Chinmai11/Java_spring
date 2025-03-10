package com.kalthko.employee_crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  int id;

    @NotBlank(message = "Name cannot be empty ")
   private  String name;
   private String department;

   @Max(value = 500, message = "Salary cannot be more than 500")
   private int salary;

   @NotBlank(message = "Country name cannot be empty")
   private String countryName;
   @Embedded
   private Country country;

}
