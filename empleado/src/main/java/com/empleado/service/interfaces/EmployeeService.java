package com.empleado.service.interfaces;

import com.empleado.entities.Employee;
import com.empleado.exceptions.EmployeeNotFound;
import com.empleado.exceptions.EmployeeRegistered;
import com.empleado.exceptions.DuplicateAttribute;
import com.empleado.http.response.OvertimeByEmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {


    Employee registerEmployee(Employee employee) throws EmployeeRegistered, DuplicateAttribute;

    Employee updateEmployee(Employee employee) throws EmployeeNotFound;

    Employee getEmployee(String idCard) throws EmployeeNotFound;

    List<Employee> getAllEmployees();

    String deleteEmployee(String idCard) throws EmployeeNotFound;

    OvertimeByEmployeeResponse findOvertimeByEmployeeId(String idCard) throws EmployeeNotFound;

}
