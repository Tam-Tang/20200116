package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.MaintenanceCycleTop10DTO;
import com.ags.modules.sys.entity.ThMaintenanceCycleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 维修周期超时记录
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
@Mapper
public interface ThMaintenanceCycleDao extends BaseMapper<ThMaintenanceCycleEntity> {
	List<MaintenanceCycleTop10DTO> queryMaintenanceCycleTop10(@Param("maintainStep")String maintainStep,@Param("timeType") String timeType);
}
