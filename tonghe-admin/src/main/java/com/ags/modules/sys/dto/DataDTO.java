package com.ags.modules.sys.dto;

import lombok.Data;
import java.util.List;

@Data
public class DataDTO {
    private String lineName;
    private String schedulQty;
    private String actualQty;
    private String achRate;
    private String standardAchRate;
    private String status;
    private List<String> catrgorys; //低于标准值需要显示相应的大类信息

}
