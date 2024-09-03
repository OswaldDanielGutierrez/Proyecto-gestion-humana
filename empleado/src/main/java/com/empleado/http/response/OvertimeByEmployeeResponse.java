package com.empleado.http.response;

import com.empleado.dto.OvertimeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OvertimeByEmployeeResponse {

    private String employeeName;
    private List<OvertimeDTO> overtimeList;

}
