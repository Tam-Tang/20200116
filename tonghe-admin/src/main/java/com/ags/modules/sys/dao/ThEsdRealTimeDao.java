package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.ESDWarnDTO;
import com.ags.modules.sys.dto.EsdPlaceDTO;
import com.ags.modules.sys.dto.EsdStatusByPlaceDTO;
import com.ags.modules.sys.entity.ThEsdRealTimeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ESD实时表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
@Mapper
public interface ThEsdRealTimeDao extends BaseMapper<ThEsdRealTimeEntity> {

    /**
     * 查询最新的 ESD数据
     * @return
     */
    List<EsdStatusByPlaceDTO> queryEsdStatusByPlace();

    List<EsdStatusByPlaceDTO> queryEsdStatusByPlaceMan();
    List<EsdStatusByPlaceDTO> queryEsdStatusByPlaceMachine();

    List<EsdPlaceDTO> queryEsdStatusByPlaceManESD();
    List<EsdPlaceDTO> queryEsdStatusByPlaceMachineESD();

    List<ESDWarnDTO> queryManESDWarn();
    List<ESDWarnDTO> queryMachineESDWarn();

}
