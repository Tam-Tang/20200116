package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThMaintenanceConfigEntity;

import java.util.Map;

/**
 * 维修看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThMaintenanceConfigService extends IService<ThMaintenanceConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

