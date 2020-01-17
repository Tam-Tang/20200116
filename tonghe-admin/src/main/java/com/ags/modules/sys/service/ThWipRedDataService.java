package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThWipRedDataEntity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-02 15:41:23
 */
public interface ThWipRedDataService extends IService<ThWipRedDataEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

