package com.payroll.client;

import com.payroll.dto.OvertimeDTO;
import com.payroll.dto.OvertimeValueDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-overtime", url = "localhost:9090/api/v1/overtime")
public interface OvertimeClient {

    @GetMapping("/findAllOvertime")
    List<OvertimeDTO> findAllOvertime();

    @GetMapping("/totalOvertimeValue/{employeeIdCard}")
    OvertimeValueDto getOvertimeValue(@PathVariable String employeeIdCard);


}
