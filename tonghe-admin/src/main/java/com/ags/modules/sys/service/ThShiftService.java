package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThShiftEntity;

import java.util.Map;

/**
 * 班别信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-11 16:35:07
 */
public interface ThShiftService extends IService<ThShiftEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

