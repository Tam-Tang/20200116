package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.MaintenanceDetailPieDTO;
import com.ags.modules.sys.entity.ThMaintenanceDetailEntity;
import com.ags.modules.sys.vo.MaintenancetrendBrforeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 维修明细表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
@Mapper
public interface ThMaintenanceDetailDao extends BaseMapper<ThMaintenanceDetailEntity> {
    MaintenanceDetailPieDTO queryMaintenanceDetailPie(@Param("maintainStep")String maintainStep, @Param("timeType") String timeType);

    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore4();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore8();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore12();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore16();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore20();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore24();

}
