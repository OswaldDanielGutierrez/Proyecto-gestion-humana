package com.empleado.controller;

import com.empleado.entities.Employee;
import com.empleado.exceptions.DuplicateAttribute;
import com.empleado.exceptions.EmployeeNotFound;
import com.empleado.exceptions.EmployeeRegistered;
import com.empleado.http.response.OvertimeByEmployeeResponse;
import com.empleado.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws EmployeeRegistered, DuplicateAttribute {
        return new ResponseEntity<>(employeeService.registerEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> modifyEmployee (@RequestBody Employee employee) throws EmployeeNotFound {
        return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/get/{idCard}")
    public ResponseEntity<Employee> findEmployee(@PathVariable String idCard) throws EmployeeNotFound {
        return new ResponseEntity<>(employeeService.getEmployee(idCard), HttpStatus.OK);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idCard}")
    public ResponseEntity<String> removeEmployee(@PathVariable String idCard) throws EmployeeNotFound {
        return new ResponseEntity<>(employeeService.deleteEmployee(idCard), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/overtimeByEmployee/{idCard}")
    public ResponseEntity<OvertimeByEmployeeResponse> overtimeByEmployee(@PathVariable String idCard) throws EmployeeNotFound{
        return new ResponseEntity<>(employeeService.findOvertimeByEmployeeId(idCard), HttpStatus.OK);
    }

}
