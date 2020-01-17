package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.UndeliveryPerByQtyDTO;
import com.ags.modules.sys.entity.ThUndeliveredDetailsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 未交付明细表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@Mapper
public interface ThUndeliveredDetailsDao extends BaseMapper<ThUndeliveredDetailsEntity> {
    List<UndeliveryPerByQtyDTO> queryUndeliveryPerByQty();
}
