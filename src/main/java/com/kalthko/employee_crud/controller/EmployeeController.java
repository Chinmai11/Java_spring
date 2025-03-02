package com.kalthko.employee_crud.controller;

import com.kalthko.employee_crud.model.Employee;
import com.kalthko.employee_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public List<Employee> getEmployeeDetails(){
       return service.getEmployeeDetails();

    }

    @GetMapping("/employees/{Id}")
    public Employee getEmployeeById(@PathVariable int Id){
        return service.getEmployeeById(Id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee emp) {
        return service.addEmployee(emp); // Returns 200 OK with saved data
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
