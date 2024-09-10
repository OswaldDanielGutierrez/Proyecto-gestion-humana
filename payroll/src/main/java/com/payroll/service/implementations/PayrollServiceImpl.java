package com.payroll.service.implementations;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
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

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



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

    @Override
    public ByteArrayInputStream generarNominaPDF() {

        PayrollResponse payrollResponse = nomina();
        List<PayrollDTO> payrollDTOList = payrollResponse.getPayrollList();

        // Crear un documento PDF
        Document document = new Document(PageSize.A4.rotate()); // Formato horizontal
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Crear la tabla con el número de columnas que deseas
            PdfPTable table = new PdfPTable(10); // 10 columnas

            // Añadir los encabezados de la tabla
            table.addCell("Nombre");
            table.addCell("Cédula");
            table.addCell("Salario");
            table.addCell("Horas Extras");
            table.addCell("Subtotal");
            table.addCell("Devengado");
            table.addCell("Salud");
            table.addCell("Pensión");
            table.addCell("Deducción");
            table.addCell("Total");

            // Añadir los datos de la nómina
            for (PayrollDTO payroll : payrollDTOList) {
                table.addCell(payroll.getName());
                table.addCell(payroll.getIdCard());
                table.addCell(String.valueOf(payroll.getSalary()));
                table.addCell(String.valueOf(payroll.getTotalOvertimeValue()));
                table.addCell(String.valueOf(payroll.getSubTotal()));
                table.addCell(String.valueOf(payroll.getDevengado()));
                table.addCell(String.valueOf(payroll.getHealthContrib()));
                table.addCell(String.valueOf(payroll.getPensionContrib()));
                table.addCell(String.valueOf(payroll.getDeduccion()));
                table.addCell(String.valueOf(payroll.getTotal()));
            }

            // Añadir la tabla al documento
            document.add(table);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }




}
