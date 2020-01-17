package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThQualityConfigEntity;

import java.util.Map;

/**
 * 质量看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThQualityConfigService extends IService<ThQualityConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

