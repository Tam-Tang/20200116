package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.EquipmentGL1hDTO;
import com.ags.modules.sys.dto.MachineMobilityRateDTO;
import com.ags.modules.sys.dto.MobilityRateDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThEuipmentStatusEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备运行状态表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
public interface ThEuipmentStatusService extends IService<ThEuipmentStatusEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThEuipmentStatusEntity> listNew();

    /**
     * 查询线别的稼动率
     * @return
     */
    List<MobilityRateDTO> queryMobilityByLine();

    /**
     * 查询设备的稼动率
     * @return
     */
    List<MachineMobilityRateDTO> queryMobilityByMachine();

    /**
     * 查询设备状态停机大于1小时的
     * @return
     */
    List<EquipmentGL1hDTO> queryEquipmentGL1h();

}

