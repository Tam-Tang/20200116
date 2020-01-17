package com.ags.modules.sys.vo;

import lombok.Data;

/**
 * 站点良率VO
 */
@Data
public class YieldByStationVO {

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


    private String starndardYield;

}
