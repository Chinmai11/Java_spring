package com.kalthko.employee_crud.service;


import com.kalthko.employee_crud.client.CountryClient;
import com.kalthko.employee_crud.exception.EmployeeNotfoundException;
import com.kalthko.employee_crud.model.Country;
import com.kalthko.employee_crud.model.Employee;
import com.kalthko.employee_crud.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
//import static java.util.stream.Nodes.collect;

@Service
public class EmployeeService {


    private final EmployeeRepo repo;
    private final CountryClient client;

    public EmployeeService(EmployeeRepo repo, CountryClient client) {
        this.repo = repo;
        this.client = client;
    }


    public List<Employee> getEmployeeDetails(){
        return repo.findAll()
                    .stream()
                .filter(emp -> emp.getSalary()<=500)
                .collect(Collectors.toList());
    }

    public Employee getEmployeeById(int Id){
        Employee emp = repo.findById(Id)
                .orElseThrow(() -> new EmployeeNotfoundException("Employee not found with ID :" + Id));

        if (emp.getName() == null || emp.getName().trim().isEmpty()) {
            throw new EmployeeNotfoundException("Employee name is empty for ID: " + Id);
        }
        return emp;
    }

    public Employee addEmployee(Employee emp) {
        if (emp.getName() == null || emp.getName().trim().isEmpty()) {
            throw new EmployeeNotfoundException("Employee name cannot be empty");
        }

        if (emp.getSalary() > 500) {
            throw new IllegalArgumentException("Salary cannot be more than 500");
        }


        Country country = client.fetchCountryDetails(emp.getCountryName());
        emp.setCountry(country);
        return repo.save(emp);

    }

    public Employee updateEmployee(Employee emp){
        Country country = client.fetchCountryDetails(emp.getCountryName());
        emp.setCountry(country);
        return repo.save(emp);
    }


    public void deleteEmployee(int Id){

        repo.deleteById(Id);
    }



}
