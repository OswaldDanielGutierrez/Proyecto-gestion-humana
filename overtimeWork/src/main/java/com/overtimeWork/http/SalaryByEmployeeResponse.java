package com.overtimeWork.http;

import com.overtimeWork.dto.EmployeeDto;
import com.overtimeWork.entities.Overtime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SalaryByEmployeeResponse {


    EmployeeDto employee;
    List<Overtime> overtimeList;
}
