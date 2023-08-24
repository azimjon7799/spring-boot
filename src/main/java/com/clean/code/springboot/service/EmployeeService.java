package com.clean.code.springboot.service;

import com.clean.code.springboot.domain.Employee;
import com.clean.code.springboot.repository.EmployeeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    public EmployeeService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepo.findAll();
    }

    public Employee findOne(Long id){
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee findByName(String name){
        return employeeRepo.findByName(name);
    }

    public List<Employee> findByQuery(String name){
        return employeeRepo.findByNameQuery(name);
    }

    public List<Employee> findAllByNameLike(String name){
        return employeeRepo.findAllByNameLike(name);
    }

    public List<Employee> findAllByParam(String name){
        return employeeRepo.findAllByLike(name);
    }
    public void delete(Long id) {
        Employee employee = this.findOne(id);
        employeeRepo.delete(employee);
    }

}
