package com.kalthko.employee_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Document(collection = "employee")
public class Employee {
    @Id
   private  int id;
   private  String name;
   private String department;
   private int salary;
}
