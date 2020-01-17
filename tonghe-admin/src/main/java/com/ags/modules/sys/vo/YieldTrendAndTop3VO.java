package com.ags.modules.sys.vo;

import lombok.Data;

@Data
public class YieldTrendAndTop3VO {

    /**
     * 站点名称
     */
    private String stationName;
    /**
     * 站点编号
     */
    private String stationId;

    private YeildTrendVO yeildTrend;

    private BadTypeVO[]  badTypeTop3;

}
