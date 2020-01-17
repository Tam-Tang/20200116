package com.ags.modules.sys.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class LineChartVO {
    private String titleText;
    private List<String> xaxis;
    private List<BigDecimal> seriesData;
}
