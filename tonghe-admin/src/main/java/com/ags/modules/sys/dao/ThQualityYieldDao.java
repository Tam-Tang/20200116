package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.BadTypeTop3DTO;
import com.ags.modules.sys.dto.YieldByStationDTO;
import com.ags.modules.sys.dto.YieldTrendByStationDTO;
import com.ags.modules.sys.entity.ThQualityYieldEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 各产线良率标准表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 09:04:35
 */
@Mapper
public interface ThQualityYieldDao extends BaseMapper<ThQualityYieldEntity> {
    /**
     * 查询不同时间段的站点  平均  良率信息
     * @param timeType
     * @return
     */
	List<YieldByStationDTO> queryYieldByStation(@Param("timeType") String timeType,@Param("timeStart")String timeStart,@Param("timeEnd")String timeEnd);

    /**
     * 查询站点 标准平均值
     * @return
     */
	List<YieldByStationDTO> queryStandardYieldByStation();

    /**
     * 查询产线站点的良率趋势数据
     * @param timeType
     * @return
     */
	List<YieldTrendByStationDTO> queryStationYieldTrend(@Param("timeType") String timeType,@Param("timeStart")String timeStart,@Param("timeEnd")String timeEnd);

    /**
     * 查询该事件类型下趋势的时间集合对象
     * @param timeType
     * @return
     */
    List<String> queryTimeByTimeType(@Param("timeType") String timeType);

    List<BadTypeTop3DTO>  queryBadTypeTop3ByStation(@Param("timeType")String timeType,@Param("timeStart")String timeStart,@Param("timeEnd")String timeEnd);

    String queryTimeRange(@Param(value = "timeType") String timeType);

}
