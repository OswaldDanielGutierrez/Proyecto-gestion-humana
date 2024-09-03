package com.payroll.service.interfaces;

import com.payroll.http.PayrollResponse;
import org.springframework.stereotype.Service;

@Service
public interface PayrollService {

    PayrollResponse nomina();
}
