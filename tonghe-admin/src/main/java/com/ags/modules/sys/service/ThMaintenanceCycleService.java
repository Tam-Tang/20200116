package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.MaintenanceCycleTop10DTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThMaintenanceCycleEntity;

import java.util.List;
import java.util.Map;

/**
 * 维修周期超时记录
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThMaintenanceCycleService extends IService<ThMaintenanceCycleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MaintenanceCycleTop10DTO> queryMaintenanceCycleTop10(String maintainStep, String timeType);

}

