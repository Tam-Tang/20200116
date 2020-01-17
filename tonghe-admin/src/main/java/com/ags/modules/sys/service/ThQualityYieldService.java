package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.BadTypeTop3DTO;
import com.ags.modules.sys.dto.YieldByStationDTO;
import com.ags.modules.sys.dto.YieldTrendByStationDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThQualityYieldEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 各产线良率标准表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 09:04:35
 */
public interface ThQualityYieldService extends IService<ThQualityYieldEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<YieldByStationDTO> queryYieldByStation(String timeType,String timeRange);

    /**
     * 站点标准 平均  良率
     * @return
     */
    Map<String,YieldByStationDTO> queryStandardYieldByStation();

    /**
     * 查询产线站点良率趋势数据 不在对象里面的就是为0
     * @param timeType
     * @return
     */
    Map<String,Map<String,YieldTrendByStationDTO>> queryStationYieldTrend(String timeType,String timeRange);

    /**
     * 查询该事件类型下趋势的时间集合对象
     * @param timeType
     * @return
     */
    List<String> queryTimeByTimeType(String timeType);

    /**
     * 按照站查询TOP3 不良类别
     * @param timeType
     * @return
     */
    List<BadTypeTop3DTO>  queryBadTypeTop3ByStation(String timeType,String timeRange);

    String queryTimeRange(String timeType);

}

