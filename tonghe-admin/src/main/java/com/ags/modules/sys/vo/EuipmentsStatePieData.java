package com.ags.modules.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 设备状态饼图对象
 */
@Data
@AllArgsConstructor
public class EuipmentsStatePieData {
    /**
     * 正常
     */
    private String ok;
    /**
     * 待机
     */
    private String wait;
    /**
     * 异常
     */
    private String fail;
    /**
     * 离线
     */
    private String offline;
    /**
     * 停机
     */
    private String stop;
}
