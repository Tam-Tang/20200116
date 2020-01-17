package com.ags.modules.sys.dto;

import lombok.Data;

@Data
public class UndeliveryPerByQtyDTO {
    private String orderStatus;
    private String name;
    private String qty;
    private String percent;
}
