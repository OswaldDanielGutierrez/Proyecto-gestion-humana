package com.overtimeWork.service.implementations;

import com.overtimeWork.client.EmployeeClient;
import com.overtimeWork.dto.EmployeeDto;
import com.overtimeWork.entities.Overtime;
import com.overtimeWork.http.OvertimeValueByEmployeeResponse;
import com.overtimeWork.http.SalaryByEmployeeResponse;
import com.overtimeWork.persistance.OvertimeRepository;
import com.overtimeWork.service.interfaces.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeRepository overtimeRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @Override
    public Overtime addHours(Overtime overtime) {
        return overtimeRepository.save(overtime);
    }

    @Override
    public List<Overtime> listAllOvertime(){
        return overtimeRepository.findAll();
    }

    @Override
    public List<Overtime> searchOvertimeHours(String employeeIdCard) {
        return overtimeRepository.findAllInCurrentMonth(employeeIdCard);
    }

    @Override
    public SalaryByEmployeeResponse getSalaryByEmployee(String employeeIdCard) {

        EmployeeDto employeeDto = employeeClient.getEmployeeSalary(employeeIdCard);
        List<Overtime> overtimeList = overtimeRepository.findAllInCurrentMonth(employeeIdCard);

        double daysInMonth = 30;
        double dayTimeHours = 8;

        Map<String, Double> multiplierMap = new HashMap<>();
        multiplierMap.put("dia", 1.25);
        multiplierMap.put("noche", 1.75);
        multiplierMap.put("festivo dia", 1.75);
        multiplierMap.put("festivo noche", 2.10);
        multiplierMap.put("hora festivo dia", 2.00);
        multiplierMap.put("hora festivo noche", 2.50);

        for (Overtime overtime : overtimeList) {
            double multiplier = multiplierMap.getOrDefault(overtime.getDayNight().toLowerCase(), 1.0);
            double hourlyRateMultiplier = employeeDto.getSalary() / daysInMonth / dayTimeHours * multiplier;
            overtime.setPrice(hourlyRateMultiplier);
        }

        return SalaryByEmployeeResponse.builder()
                .employee(employeeDto)
                .overtimeList(overtimeList)
                .build();
    }

    @Override
    public OvertimeValueByEmployeeResponse totalOvertimeValue(String employeeIdCard) {

        EmployeeDto employeeDto = employeeClient.getEmployeeSalary(employeeIdCard);
        List<Overtime> overtimeList = overtimeRepository.findAllInCurrentMonth(employeeIdCard);

        double overtimeValue = 0;
        for (Overtime overtime: overtimeList){
            overtimeValue += overtime.getPrice();
        }

        return OvertimeValueByEmployeeResponse.builder()
                .name(employeeDto.getName())
                .overtimeValue(overtimeValue)
                .build();
    }


}
