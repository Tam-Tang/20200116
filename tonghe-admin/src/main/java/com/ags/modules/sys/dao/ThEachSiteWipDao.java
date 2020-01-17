package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.EachSiteWipDataDTO;
import com.ags.modules.sys.entity.ThEachSiteWipEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 各站点WIP
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@Mapper
public interface ThEachSiteWipDao extends BaseMapper<ThEachSiteWipEntity> {
    List<EachSiteWipDataDTO> queryList(@Param(value = "timeType")String timeType,@Param(value = "timeStart")String timeStart,@Param(value = "timeEnd")String timeEnd);
}
