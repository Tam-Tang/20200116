package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThRepairStabdardTimeEntity;

import java.util.Map;

/**
 * 员工维修标准工时表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThRepairStabdardTimeService extends IService<ThRepairStabdardTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

