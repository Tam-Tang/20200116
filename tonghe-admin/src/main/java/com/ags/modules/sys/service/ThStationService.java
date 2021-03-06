package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThStationEntity;

import java.util.Map;

/**
 * 站点基础信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThStationService extends IService<ThStationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

