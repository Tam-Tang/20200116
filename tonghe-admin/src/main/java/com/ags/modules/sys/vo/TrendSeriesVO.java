package com.ags.modules.sys.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TrendSeriesVO {
    private String name;
    private List<BigDecimal> data;
}
