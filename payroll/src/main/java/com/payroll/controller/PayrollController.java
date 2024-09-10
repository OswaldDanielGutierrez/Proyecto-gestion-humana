package com.payroll.controller;

import com.payroll.http.PayrollResponse;
import com.payroll.service.interfaces.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/v1/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/getPayroll")
    public ResponseEntity<PayrollResponse> getPayroll(){
        return new ResponseEntity<>(payrollService.nomina(), HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> descargarNominaPDF() {
        ByteArrayInputStream bis = payrollService.generarNominaPDF();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=nomina.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
