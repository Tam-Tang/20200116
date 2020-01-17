package com.ags.modules.sys.vo;

import lombok.Data;

@Data
public class YeildTrendVO {
    /**
     * 良率
     */
    private String[] yields;

    /**
     * 时间  可以是 周  月  日
     */
    private String[] times;
}
