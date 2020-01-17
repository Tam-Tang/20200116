package com.ags.modules.sys.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PieChartVO {
    private String titleText;
    private List<String> legendData;
    private List<BigDecimal> seriesData;
}
