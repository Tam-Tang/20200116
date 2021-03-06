package com.ags.modules.sys.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CtoDirectPassRateVO {
    private String title;
    private List<String> xaxis;
    private List<BigDecimal> goalData;
    private List<BigDecimal> trueData;
}
