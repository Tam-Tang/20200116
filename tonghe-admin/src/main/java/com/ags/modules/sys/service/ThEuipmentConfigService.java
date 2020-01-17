package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThEuipmentConfigEntity;

import java.util.Map;

/**
 * 工序看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThEuipmentConfigService extends IService<ThEuipmentConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

