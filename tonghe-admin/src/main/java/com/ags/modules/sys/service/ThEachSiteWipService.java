package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.EachSiteWipDataDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThEachSiteWipEntity;

import java.util.List;
import java.util.Map;

/**
 * 各站点WIP
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThEachSiteWipService extends IService<ThEachSiteWipEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<EachSiteWipDataDTO> queryList(String timeType,String timeRange);
}

