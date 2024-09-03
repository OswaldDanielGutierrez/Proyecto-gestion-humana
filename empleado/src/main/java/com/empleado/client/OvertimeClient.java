package com.empleado.client;


import com.empleado.dto.OvertimeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "msvc-overtime", url = "localhost:9090/api/v1/overtime")
public interface OvertimeClient {

    @GetMapping("/find-overtime-hours/{employeeIdCard}")
    List<OvertimeDTO> findAllOvertimeByEmployeeIdCard(@PathVariable String employeeIdCard);

}
