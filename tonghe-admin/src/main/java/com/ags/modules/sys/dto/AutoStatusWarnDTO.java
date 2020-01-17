package com.ags.modules.sys.dto;

import lombok.Data;

@Data
public class AutoStatusWarnDTO {
    private String euipmentType;
    private String euipmentName;
    private String warnMsg;
    private String times;
}
