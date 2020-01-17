package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThMateeralConfigEntity;

import java.util.Map;

/**
 * 抛料看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThMateeralConfigService extends IService<ThMateeralConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

