package com.ags.common.chartVO;

import lombok.Data;

/**
 * 百度echart
 * 饼图对象
 */
@Data
public class PieChartVO {
    private String[] legendData;
    private String[] seriesData;

    @Data
    public class serieData{
        private String name;
        private String value;
    }
}
