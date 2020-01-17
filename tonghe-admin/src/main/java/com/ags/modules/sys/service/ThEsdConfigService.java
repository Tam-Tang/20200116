package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThEsdConfigEntity;

import java.util.Map;

/**
 * 温湿度ESD看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThEsdConfigService extends IService<ThEsdConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

