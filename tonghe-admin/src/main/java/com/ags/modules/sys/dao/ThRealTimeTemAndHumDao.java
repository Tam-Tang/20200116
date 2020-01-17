package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.HumWarnDTO;
import com.ags.modules.sys.entity.ThAutoStatusEntity;
import com.ags.modules.sys.entity.ThRealTimeTemAndHumEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 温湿度实时表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:38
 */
@Mapper
public interface ThRealTimeTemAndHumDao extends BaseMapper<ThRealTimeTemAndHumEntity> {

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThRealTimeTemAndHumEntity> listNew();

    List<HumWarnDTO> queryTempHumWarn();
}
