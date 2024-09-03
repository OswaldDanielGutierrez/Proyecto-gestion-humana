package com.payroll.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeIdCard;

    private double transportAid;

    private String totalDevengado;

    private double healthContrib;

    private double pensionContrib;

    private double fsp;

    private double retefuente;

    private double totalDeduction;

    private double totalPayroll;
    /////////////













}
