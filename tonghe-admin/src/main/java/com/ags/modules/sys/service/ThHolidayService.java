package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThHolidayEntity;

import java.util.Map;

/**
 * 节假日表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThHolidayService extends IService<ThHolidayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

