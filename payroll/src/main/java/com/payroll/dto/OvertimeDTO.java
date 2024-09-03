package com.payroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OvertimeDTO {

    private String name;
    private double overtimeValue;
    private String employeeIdCard;
    private double hourlyRate;
}
