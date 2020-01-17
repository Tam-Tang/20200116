package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThProLineDefectDetailsEntity;

import java.util.Map;

/**
 * 产线不良明细表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 10:08:54
 */
public interface ThProLineDefectDetailsService extends IService<ThProLineDefectDetailsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

