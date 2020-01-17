package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThRateRedDataEntity;

import java.util.Map;

/**
 * 達成率警戒值配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-02 15:41:24
 */
public interface ThRateRedDataService extends IService<ThRateRedDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

