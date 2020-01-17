package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.AutoStatusDTO;
import com.ags.modules.sys.dto.AutoStatusWarnDTO;
import com.ags.modules.sys.entity.ThEuipmentStatusEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThAutoStatusEntity;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 12:55:25
 */
public interface ThAutoStatusService extends IService<ThAutoStatusEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThAutoStatusEntity> listNew();

    List<AutoStatusDTO> queryStatusBylistNew();

    List<AutoStatusWarnDTO> queryWarn5BylistNew();
}

