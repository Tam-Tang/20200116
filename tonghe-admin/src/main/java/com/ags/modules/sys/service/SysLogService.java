

package com.ags.modules.sys.service;


import com.ags.modules.sys.entity.SysLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *

 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
