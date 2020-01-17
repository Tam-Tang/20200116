package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.MaintenanceNumberDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThMaintenanceNumberEntity;

import java.util.Map;

/**
 * 维修数量表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThMaintenanceNumberService extends IService<ThMaintenanceNumberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MaintenanceNumberDTO queryMaintenanceNumber(String timeType);

}

