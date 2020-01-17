package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThOrderConfigEntity;

import java.util.Map;

/**
 * 订单看板配置表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThOrderConfigService extends IService<ThOrderConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

