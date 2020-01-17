package com.ags.modules.sys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 日所有机器抛料对象
 */
@Data
@AllArgsConstructor
public class DayMachineThrowingRateDTO {
    private String setQty;
    private String throwQty;
    private String percent;
}
