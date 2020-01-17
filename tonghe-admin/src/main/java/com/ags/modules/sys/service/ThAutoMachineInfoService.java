package com.ags.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThAutoMachineInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 設備基本信息數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-29 10:43:53
 */
public interface ThAutoMachineInfoService extends IService<ThAutoMachineInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThAutoMachineInfoEntity> listNew();
}

