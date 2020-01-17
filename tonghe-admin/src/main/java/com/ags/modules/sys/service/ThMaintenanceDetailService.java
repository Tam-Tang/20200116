package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.MaintenanceDetailPieDTO;
import com.ags.modules.sys.vo.MaintenancetrendBrforeVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThMaintenanceDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 维修明细表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThMaintenanceDetailService extends IService<ThMaintenanceDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);

    MaintenanceDetailPieDTO queryMaintenanceDetailPie(String maintainStep, String timeType);

    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore4();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore8();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore12();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore16();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore20();
    List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore24();
}

