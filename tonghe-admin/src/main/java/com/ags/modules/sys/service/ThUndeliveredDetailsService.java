package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.UndeliveryPerByQtyDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThUndeliveredDetailsEntity;

import java.util.List;
import java.util.Map;

/**
 * 未交付明细表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThUndeliveredDetailsService extends IService<ThUndeliveredDetailsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UndeliveryPerByQtyDTO> queryUndeliveryPerByQty();

}

