package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThAutoConfigEntity;

import java.util.Map;

/**
 * 自动化看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:12:54
 */
public interface ThAutoConfigService extends IService<ThAutoConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

