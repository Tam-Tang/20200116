package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.DataCategoryDTO;
import com.ags.modules.sys.dto.DataDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThProLineAchRateEntity;

import java.util.List;
import java.util.Map;

/**
 * 产线达成率
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThProLineAchRateService extends IService<ThProLineAchRateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<DataDTO> queryList(String timeType,String timeRange);
    List<DataCategoryDTO> queryListCatrgory(String timeType, String timeRange);

    String queryTimeRange(String timeType);

    DataDTO queryNowWeekTop1NoAch();
    DataDTO queryNowWeekTop1Ach();
    DataDTO queryPreWeekTop1NoAch();
    DataDTO queryPreWeekTop1Ach();
}

