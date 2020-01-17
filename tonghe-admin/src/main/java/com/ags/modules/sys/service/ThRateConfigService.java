package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThRateConfigEntity;

import java.util.Map;

/**
 * 达成率看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThRateConfigService extends IService<ThRateConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

