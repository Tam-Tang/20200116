package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThProductUphEntity;

import java.util.Map;

/**
 * 产线标准产出信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThProductUphService extends IService<ThProductUphEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

