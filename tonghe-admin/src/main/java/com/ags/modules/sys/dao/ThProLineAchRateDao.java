package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.DataCategoryDTO;
import com.ags.modules.sys.dto.DataDTO;
import com.ags.modules.sys.entity.ThProLineAchRateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产线达成率
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@Mapper
public interface ThProLineAchRateDao extends BaseMapper<ThProLineAchRateEntity> {
    List<DataDTO> queryList(@Param(value = "timeType") String timeType,@Param(value = "timeStart")String timeStart,@Param(value = "timeEnd")String timeEnd);
    List<DataCategoryDTO> queryListCatrgory(@Param(value = "timeType") String timeType, @Param(value = "timeStart")String timeStart, @Param(value = "timeEnd")String timeEnd);


    String queryTimeRange(@Param(value = "timeType") String timeType);

    DataDTO queryNowWeekTop1NoAch();
    DataDTO queryNowWeekTop1Ach();
    DataDTO queryPreWeekTop1NoAch();
    DataDTO queryPreWeekTop1Ach();

}
