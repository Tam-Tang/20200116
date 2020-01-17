package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThProductStandardOutEntity;

import java.util.Map;

/**
 * 產線標準產量表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-05 14:30:56
 */
public interface ThProductStandardOutService extends IService<ThProductStandardOutEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

