package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.MachineThrowingRateDTO;
import com.ags.modules.sys.entity.ThMachineThrowingRateEntity;
import com.ags.modules.sys.dto.DayEachMachineThrowingRateDTO;
import com.ags.modules.sys.dto.DayMachineThrowingRateDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 机台抛料率
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
@Mapper
public interface ThMachineThrowingRateDao extends BaseMapper<ThMachineThrowingRateEntity> {
    /**
     * 查询日期最新的数据
     * @return
     */
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
