package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.EquipmentGL1hDTO;
import com.ags.modules.sys.dto.MachineMobilityRateDTO;
import com.ags.modules.sys.dto.MobilityRateDTO;
import com.ags.modules.sys.entity.ThEuipmentStatusEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 设备运行状态表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:37
 */
@Mapper
public interface ThEuipmentStatusDao extends BaseMapper<ThEuipmentStatusEntity> {

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThEuipmentStatusEntity> listNew();

    List<MobilityRateDTO> queryMobilityByLine();
    List<MachineMobilityRateDTO> queryMobilityByMachine();

    List<EquipmentGL1hDTO> queryEquipmentGL1h();

}
