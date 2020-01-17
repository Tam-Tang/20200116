package com.ags.common.enums;

/**
 * 看板类型枚举
 */
public enum  BoradEnums {
    AchieveRateReport("达成率管理看板","AchieveRateReport"),
    AutoEquipmentReport("自动化管理看板","AutoEquipmentReport"),
    HumitureReport("温湿度ESD实时管理看板","HumitureReport"),
    MaintainReport("维修管理看板","MaintainReport"),
    OrderReport("订单管理看板","OrderReport"),
    ProcessStateReport("工序状态管理看板","ProcessStateReport"),
    QualityReport("质量管理看板","QualityReport"),
    ThrowRateReport("抛料(物料)管理看板","ThrowRateReport"),
    VideoReport("视频监控看板","VideoReport")
    ;

    // 成员变量
    private String name;
    private String ename;

    // 构造方法
    private BoradEnums(String name, String ename) {
        this.name = name;
        this.ename = ename;
    }
}
