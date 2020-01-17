package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.MaintenanceNumberDTO;
import com.ags.modules.sys.entity.ThMaintenanceNumberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 维修数量表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
@Mapper
public interface ThMaintenanceNumberDao extends BaseMapper<ThMaintenanceNumberEntity> {

    MaintenanceNumberDTO queryMaintenanceNumber(@Param("timeType") String timeType);

}
