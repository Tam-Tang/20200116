package com.ags.modules.sys.dto;

import lombok.Data;

@Data
public class EachSiteWipDataDTO {
    private String stationName;
    private String wipQty;
    private String standardData;
    private String status; // 1---绿色，2---黄色，3---红色
}
