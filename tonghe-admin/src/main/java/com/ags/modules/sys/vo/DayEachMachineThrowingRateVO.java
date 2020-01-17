package com.ags.modules.sys.vo;

import com.ags.modules.sys.dto.MachineThrowingRateDTO;
import com.ags.modules.sys.entity.ThMachineThrowingRateEntity;
import lombok.Data;

import java.util.List;

@Data
public class DayEachMachineThrowingRateVO {
    private String percent;
    private String machineNo;

    private String lineId;
    private String lineName;
    private String throwRate;
    private String standard;

    private List<MachineThrowingRateDTO> top3;
}
