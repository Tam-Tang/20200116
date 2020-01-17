package com.ags.modules.sys.dto;

import lombok.Data;

/**
 * 站点良率趋势对象
 */
@Data
public class YieldTrendByStationDTO {

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

    /**
     * 时间  可以是 周  月  日
     */
    private String time;

}
