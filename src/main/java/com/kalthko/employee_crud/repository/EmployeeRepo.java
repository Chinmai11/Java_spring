package com.kalthko.employee_crud.repository;


import com.kalthko.employee_crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

}
