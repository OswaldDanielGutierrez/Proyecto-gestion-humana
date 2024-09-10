package com.payroll.service.interfaces;

import com.payroll.http.PayrollResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public interface PayrollService {

    PayrollResponse nomina();

    ByteArrayInputStream generarNominaPDF();
}
