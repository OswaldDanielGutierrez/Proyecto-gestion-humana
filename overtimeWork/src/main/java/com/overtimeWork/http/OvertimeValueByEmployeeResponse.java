package com.overtimeWork.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OvertimeValueByEmployeeResponse {

    private String name;
    private double overtimeValue;

}
