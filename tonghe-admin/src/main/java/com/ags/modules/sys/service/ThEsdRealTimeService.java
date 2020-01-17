package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.ESDWarnDTO;
import com.ags.modules.sys.dto.EsdPlaceDTO;
import com.ags.modules.sys.dto.EsdStatusByPlaceDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThEsdRealTimeEntity;

import java.util.List;
import java.util.Map;

/**
 * ESD实时表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
public interface ThEsdRealTimeService extends IService<ThEsdRealTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);

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

