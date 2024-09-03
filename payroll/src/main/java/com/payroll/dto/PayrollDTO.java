package com.payroll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayrollDTO {

    private String name;
    private String idCard;
    private Double salary;
    private Double totalOvertimeValue;
    private Double biweeklyPeriod;
    private Double subTotal;
    private Double transportAid;
    private Double devengado;
    private Double healthContrib;
    private Double pensionContrib;
    private Double fsp;
    private Double deduccion;
    private Double total;

}
