package com.ags.modules.sys.dto;

import lombok.Data;

@Data
public class OrderCycleTimeOutPerctDTO {
    private String totalQty;
    private String overTimeQty;
    private String percent;
}
