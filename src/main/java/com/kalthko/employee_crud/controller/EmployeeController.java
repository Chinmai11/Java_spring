package com.kalthko.employee_crud.controller;

import com.kalthko.employee_crud.model.Country;
import com.kalthko.employee_crud.model.Employee;
import com.kalthko.employee_crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.ArrayList.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService service;

      public EmployeeController(EmployeeService service){

        this.service = service;
    }


    @GetMapping("/employees")
    public List<Employee> getEmployeeDetails(){
       return service.getEmployeeDetails();

    }

    @GetMapping("/employees/{Id}")
    public Employee getEmployeeById(@PathVariable int Id){

          return service.getEmployeeById(Id);
    }



    @PostMapping("/employees")
    public Employee addEmployee(@Valid @RequestBody Employee emp) {

          return service.addEmployee(emp);
    }


    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee emp){

        service.updateEmployee(emp);
    }

    @DeleteMapping("/employees/{Id}")
    public void deleteEmployee(@PathVariable int Id){

        service.deleteEmployee(Id);
    }
}

