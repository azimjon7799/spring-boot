package com.clean.code.springboot.web.rest;

import com.clean.code.springboot.domain.Employee;
import com.clean.code.springboot.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private final EmployeeService employeeService;
    public EmployeeResource(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee employee1 = employeeService.save(employee);
        return ResponseEntity.ok(employee1);
    }

    @GetMapping("/employees")
    public  ResponseEntity findAll(){
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        Employee employee = employeeService.findOne(id);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees/name/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        Employee employee = employeeService.findByName(name);
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/employees/query/{name}")
    public ResponseEntity findByQuery(@PathVariable String name){
        List<Employee> employee = employeeService.findByQuery(name);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees/like/{name}")
    public ResponseEntity findByNameLike(@PathVariable String name){
        List<Employee> employees = employeeService.findAllByNameLike(name);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/search")
    public ResponseEntity getAllSearch(@RequestParam String name){
        List<Employee> employees = employeeService.findAllByParam(name);
        return  ResponseEntity.ok(employees);
    }

    @PatchMapping("/employees")
    public ResponseEntity update(@RequestBody Employee employee){
        Employee employee1 = employeeService.save(employee);
        return ResponseEntity.ok(employee1);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        Employee employee = employeeService.findOne(id);
        if (employee == null) {
            return ResponseEntity.badRequest().body("Date not found");
        }
        employeeService.delete(id);
        return ResponseEntity.ok("Date deleted");
    }

}
