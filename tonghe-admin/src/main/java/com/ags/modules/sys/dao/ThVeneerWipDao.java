package com.ags.modules.sys.dao;

import com.ags.modules.sys.entity.ThVeneerWipEntity;
import com.ags.modules.sys.vo.VeneerWipVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 單板WIP趨勢數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:09
 */
@Mapper
public interface ThVeneerWipDao extends BaseMapper<ThVeneerWipEntity> {
    List<VeneerWipVO> queryVeneerWip(@Param("timeType") String timeType);
}
