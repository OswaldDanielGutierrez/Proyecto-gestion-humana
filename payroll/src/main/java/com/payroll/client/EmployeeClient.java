package com.payroll.client;

import com.payroll.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "msvc-empleado", url = "localhost:8090/api/v1/employee")
public interface EmployeeClient {

    @GetMapping("/getAllEmployee")
    List<EmployeeDTO> findAllEmployee();
}
