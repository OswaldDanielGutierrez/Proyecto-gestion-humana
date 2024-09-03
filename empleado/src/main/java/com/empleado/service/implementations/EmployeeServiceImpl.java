package com.empleado.service.implementations;

import com.empleado.client.OvertimeClient;
import com.empleado.dto.OvertimeDTO;
import com.empleado.entities.Employee;
import com.empleado.exceptions.EmployeeNotFound;
import com.empleado.exceptions.EmployeeRegistered;
import com.empleado.exceptions.DuplicateAttribute;
import com.empleado.http.response.OvertimeByEmployeeResponse;
import com.empleado.persistence.EmployeeRepository;
import com.empleado.service.interfaces.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OvertimeClient overtimeClient;

    @Override
    public Employee registerEmployee(Employee employee) throws EmployeeRegistered, DuplicateAttribute {
        boolean isRegistered = employeeRepository.existsByIdCard(employee.getIdCard());
        if (isRegistered){
            throw new EmployeeRegistered("The employee with the ID card " + employee.getIdCard() + " is registered in the system.");
        }

        Optional<Employee> existingEmployee = employeeRepository.findByIdCardOrEmailOrAccountNumber(
                employee.getIdCard(), employee.getEmail(), employee.getAccountNumber()
        );

        if (existingEmployee.isPresent()){
            throw new DuplicateAttribute("Duplicate attributes detected");
        }

        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeNotFound {

        Optional<Employee> employeeOptional = employeeRepository.findByIdCard(employee.getIdCard());

        Employee employeeBBDD = employeeOptional
                .orElseThrow(() -> new EmployeeNotFound("Employee with the ID card "+ employee.getIdCard() + " has not been found"));

        BeanUtils.copyProperties(employee,employeeBBDD, "id", "idCard");


        return employeeRepository.save(employeeBBDD);
    }

    @Override
    public Employee getEmployee(String idCard) throws EmployeeNotFound {
        return employeeRepository.findByIdCard(idCard)
                .orElseThrow(() -> new EmployeeNotFound("Employee with the ID card number "+ idCard + " has not been found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String deleteEmployee(String idCard) throws EmployeeNotFound {
        Employee employee = employeeRepository.findByIdCard(idCard)
                .orElseThrow(() -> new EmployeeNotFound("Employee with the ID card number "+ idCard + " has not been found"));

        employeeRepository.delete(employee);

        return "Employee with the ID card number "+ idCard + "has been removed";
    }

    @Override
    public OvertimeByEmployeeResponse findOvertimeByEmployeeId(String idCard) throws EmployeeNotFound {

        Employee employee = employeeRepository.findByIdCard(idCard)
                .orElseThrow(() -> new EmployeeNotFound("Employee with the ID card number "+ idCard + " has not been found"));

        List<OvertimeDTO> overtimeDTOList = overtimeClient.findAllOvertimeByEmployeeIdCard(idCard);

        return OvertimeByEmployeeResponse.builder()
                .employeeName(employee.getName())
                .overtimeList(overtimeDTOList)
                .build();
    }




}
