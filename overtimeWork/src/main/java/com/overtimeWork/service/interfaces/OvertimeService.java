package com.overtimeWork.service.interfaces;

import com.overtimeWork.entities.Overtime;
import com.overtimeWork.http.OvertimeValueByEmployeeResponse;
import com.overtimeWork.http.SalaryByEmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OvertimeService {

    Overtime addHours(Overtime overtime);

    List<Overtime> listAllOvertime();

    List<Overtime> searchOvertimeHours(String employeeIdCard);

    SalaryByEmployeeResponse getSalaryByEmployee(String employeeIdCard);

    OvertimeValueByEmployeeResponse totalOvertimeValue(String employeeIdCard);

}
