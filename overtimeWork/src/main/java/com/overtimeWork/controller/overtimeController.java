package com.overtimeWork.controller;

import com.overtimeWork.entities.Overtime;
import com.overtimeWork.http.OvertimeValueByEmployeeResponse;
import com.overtimeWork.http.SalaryByEmployeeResponse;
import com.overtimeWork.service.interfaces.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/overtime")
public class overtimeController {

    @Autowired
    private OvertimeService overtimeService;

    @PostMapping("/saveOvertime")
    public ResponseEntity<Overtime> saveOvertime(@RequestBody Overtime overtime){
        return new ResponseEntity<>(overtimeService.addHours(overtime), HttpStatus.OK);
    }


    @GetMapping("/findAllOvertime")
    public ResponseEntity<List<Overtime>> findAllOvertime(){
        return new ResponseEntity<>(overtimeService.listAllOvertime(), HttpStatus.OK);
    }

    @GetMapping("/find-overtime-hours/{employeeIdCard}")
    public ResponseEntity<?> findByEmployeeIdCard(@PathVariable String employeeIdCard){
        return ResponseEntity.ok(overtimeService.searchOvertimeHours(employeeIdCard));
    }

    @GetMapping("/salaryByEmployee/{employeeIdCard}")
    public ResponseEntity<SalaryByEmployeeResponse> getSalaryByEmployeeIdCard(@PathVariable String employeeIdCard){
        return new ResponseEntity<>(overtimeService.getSalaryByEmployee(employeeIdCard), HttpStatus.OK);
    }

    @GetMapping("/totalOvertimeValue/{employeeIdCard}")
    public ResponseEntity<OvertimeValueByEmployeeResponse> getTotalOvertimeValue(@PathVariable String employeeIdCard){
        return new ResponseEntity<>(overtimeService.totalOvertimeValue(employeeIdCard), HttpStatus.OK);
    }

}
