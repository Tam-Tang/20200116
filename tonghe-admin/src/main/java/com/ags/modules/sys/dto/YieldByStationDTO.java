package com.ags.modules.sys.dto;

import lombok.Data;

/**
 * 站点良率
 */
@Data
public class YieldByStationDTO {
    /**
     * 良率
     */
    private String yield;
    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 站点编号
     */
    private String stationId;

}
