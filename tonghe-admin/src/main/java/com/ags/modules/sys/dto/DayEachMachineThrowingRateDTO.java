package com.ags.modules.sys.dto;

import com.ags.modules.sys.entity.ThMachineThrowingRateEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 日每台设备抛料对象
 */
@Data
@AllArgsConstructor
public class DayEachMachineThrowingRateDTO {
    private String lineId;
    private String lineName;
    private String machineNo;
    private String percent;
    private String standard;
    private String throwRate;
}
