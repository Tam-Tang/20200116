package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.DayEachMachineThrowingRateDTO;
import com.ags.modules.sys.dto.DayMachineThrowingRateDTO;
import com.ags.modules.sys.dto.MachineThrowingRateDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThMachineThrowingRateEntity;

import java.util.List;
import java.util.Map;

/**
 * 机台抛料率
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThMachineThrowingRateService extends IService<ThMachineThrowingRateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ThMachineThrowingRateEntity> listNew();

    /**
     * 查询当天总的设备抛料率
     * @return
     */
    DayMachineThrowingRateDTO queryDayMachineThrowingRate();

    /**
     * 当天每台机抛料率
     * @return
     */
    List<DayEachMachineThrowingRateDTO> queryDayEachMachineThrowingRate();

    /**
     * 当天每台机器抛料率前三
     * @return
     */
    List<MachineThrowingRateDTO> queryEachMachineThrowingRateTop3();
}

