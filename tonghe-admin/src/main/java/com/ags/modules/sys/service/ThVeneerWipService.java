package com.ags.modules.sys.service;

import com.ags.modules.sys.vo.VeneerWipVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThVeneerWipEntity;
import java.util.List;
import java.util.Map;

/**
 * 單板WIP趨勢數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:09
 */
public interface ThVeneerWipService extends IService<ThVeneerWipEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<VeneerWipVO> queryVeneerWip(String timeType);
}

