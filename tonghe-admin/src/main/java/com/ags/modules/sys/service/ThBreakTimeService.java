package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThBreakTimeEntity;

import java.util.Map;

/**
 * 休息时间表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 08:38:58
 */
public interface ThBreakTimeService extends IService<ThBreakTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

