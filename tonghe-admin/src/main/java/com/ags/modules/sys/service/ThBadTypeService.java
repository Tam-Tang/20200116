package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThBadTypeEntity;

import java.util.Map;

/**
 * 不良类别信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 11:49:26
 */
public interface ThBadTypeService extends IService<ThBadTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

