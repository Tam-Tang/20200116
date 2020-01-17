package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.HumWarnDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThRealTimeTemAndHumEntity;

import java.util.List;
import java.util.Map;

/**
 * 温湿度实时表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThRealTimeTemAndHumService extends IService<ThRealTimeTemAndHumEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThRealTimeTemAndHumEntity> listNew();

    List<HumWarnDTO> queryTempHumWarn();

}

