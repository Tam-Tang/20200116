package com.ags.modules.sys.service;

import com.ags.modules.sys.vo.DirectPassRateVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThDirectPassRateEntity;

import java.util.List;
import java.util.Map;

/**
 * 直通率數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:11
 */
public interface ThDirectPassRateService extends IService<ThDirectPassRateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询PCBA直通率
     * @param timeType
     * @return
     */
    List<DirectPassRateVo> queryPCBADirectPassRare(String timeType,String timeRange);

    /**
     * 查询CTO直通率
     * @param timeType
     * @return
     */
    List<DirectPassRateVo> queryCTODirectPassRare(String timeType,String timeRange);
}

