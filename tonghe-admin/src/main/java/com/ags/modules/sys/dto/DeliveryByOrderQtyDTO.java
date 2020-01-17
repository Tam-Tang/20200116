package com.ags.modules.sys.dto;

import lombok.Data;

@Data
public class DeliveryByOrderQtyDTO {

    private String time;
    private String orderTotalQty;
    private String orderDeliveryQty;
    private String orderUndeliveryQty;
    private String ordeqTy;
    private String undeliveryPercent; //未交付率
    private String deliveryPercent; //交付率
}
