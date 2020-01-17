package com.ags.modules.sys.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MachineThrowingRateDTO {

    /**
     * 数据ID
     */
    private String id;
    /**
     * 插入时间
     */
    private Date createTime;
    /**
     * 日期
     */
    private Date dataTime;
    /**
     * 线别
     */
    private String lineId;
    /**
     * 轨道编号
     */
    private String trackNo;

    /**
     * 机台编号
     */
    private String machineNo;
    /**
     * 班别
     */
    private String shiftName;
    /**
     * 物料编号
     */
    private String partNumber;
    /**
     * 置件数量
     */
    private String setQty;
    /**
     * 抛料数量
     */
    private String throwQty;
    /**
     * 抛料率
     */
    private String throwRate;
    private String setRate;
    private String rate;
}
