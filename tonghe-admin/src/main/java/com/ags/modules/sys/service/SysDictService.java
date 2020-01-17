

package com.ags.modules.sys.service;

import com.ags.modules.sys.entity.SysDictEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;

import java.util.Map;

/**
 * 数据字典
 *

 */
public interface SysDictService extends IService<SysDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

