package com.kalthko.employee_crud.service;


import com.kalthko.employee_crud.model.Employee;
import com.kalthko.employee_crud.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
     EmployeeRepo repo;


    public List<Employee> getEmployeeDetails(){
        return repo.findAll();
    }

    public Employee getEmployeeById(int Id){
        return repo.findById(Id).orElse(new Employee());
    }

    public Employee addEmployee(Employee emp){
       return repo.save(emp);
    }

    public void updateEmployee(Employee emp){
        repo.save(emp);
    }

    public void deleteEmployee(int Id){
        repo.deleteById(Id);
    }


}
