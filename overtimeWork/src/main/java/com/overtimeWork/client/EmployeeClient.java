package com.overtimeWork.client;

import com.overtimeWork.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-empleado", url = "localhost:8090/api/v1/employee")
public interface EmployeeClient {

    @GetMapping("/get/{idCard}")
    EmployeeDto getEmployeeSalary(@PathVariable String idCard);

}
