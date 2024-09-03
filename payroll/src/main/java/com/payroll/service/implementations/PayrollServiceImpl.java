package com.payroll.service.implementations;

import com.payroll.client.EmployeeClient;
import com.payroll.client.OvertimeClient;
import com.payroll.dto.EmployeeDTO;
import com.payroll.dto.OvertimeValueDto;
import com.payroll.dto.PayrollDTO;
import com.payroll.http.PayrollResponse;
import com.payroll.service.interfaces.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private OvertimeClient overtimeClient;


    @Override
    public PayrollResponse nomina() {

        List<EmployeeDTO> employeeList = employeeClient.findAllEmployee();

        List<PayrollDTO> payrollDTOListList = new ArrayList<>();

        for (EmployeeDTO employee: employeeList){

            String employeeIdCard = employee.getIdCard();
            OvertimeValueDto overtimeValueDto = overtimeClient.getOvertimeValue(employeeIdCard);

            PayrollDTO payroll = new PayrollDTO();

            double SMLMV = 1300000;
            double transportAid = 0;

            double salary = employee.getSalary();
            double biweekly = salary/2;
            double totalOvertimeValue = overtimeValueDto.getOvertimeValue();
            double subTotal = salary + totalOvertimeValue;
            double healthRate = 0.04;
            double pensionRate = 0.04;
            double fspRate = 0.01;

            payroll.setIdCard(employee.getIdCard());
            payroll.setName(employee.getName());
            payroll.setSalary(salary);
            payroll.setBiweeklyPeriod(biweekly);
            payroll.setTotalOvertimeValue(totalOvertimeValue);
            payroll.setSubTotal(subTotal);

            if (salary <= SMLMV*2 ){
                payroll.setTransportAid(transportAid + 162000);
            } else{
                payroll.setTransportAid(transportAid);
            }

            if (salary >= salary*4){
                payroll.setFsp(salary*fspRate);
            } else {
                payroll.setFsp(0.0);
            }



            double devengado = subTotal + transportAid;
            payroll.setDevengado(devengado);

            double healthContrib = salary*healthRate;
            double pensionContrib = salary*pensionRate;
            double deduccion = healthContrib+pensionContrib;

            payroll.setHealthContrib(healthContrib);
            payroll.setPensionContrib(pensionContrib);
            payroll.setDeduccion(deduccion);

            payroll.setTotal(devengado-deduccion);


            payrollDTOListList.add(payroll);
        }

        return PayrollResponse.builder()
                .payrollList(payrollDTOListList)
                .build();
    }

}
