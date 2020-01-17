package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.AutoStatusDTO;
import com.ags.modules.sys.dto.AutoStatusWarnDTO;
import com.ags.modules.sys.entity.ThAutoStatusEntity;
import com.ags.modules.sys.entity.ThEuipmentStatusEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${comments}
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 12:55:25
 */
@Mapper
public interface ThAutoStatusDao extends BaseMapper<ThAutoStatusEntity> {

    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThAutoStatusEntity> listNew();

    List<AutoStatusDTO> queryStatusBylistNew();

    List<AutoStatusWarnDTO> queryWarn5BylistNew();
}
