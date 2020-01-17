package com.ags.modules.sys.dto;

import lombok.Data;

import java.util.List;

/**
 * ESD状态 正常数量和 异常数量
 */
@Data
public class EsdStatusByPlaceDTO {
    private String ok;
    private String fail;
    private String place;
    private List<EsdPlaceDTO> failEsd;
}
