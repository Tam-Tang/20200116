package com.ags.modules.sys.dao;

import com.ags.modules.sys.entity.ThAutoMachineInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 設備基本信息數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-29 10:43:53
 */
@Mapper
public interface ThAutoMachineInfoDao extends BaseMapper<ThAutoMachineInfoEntity> {


    /**
     * 查询日期最新的数据
     * @return
     */
    List<ThAutoMachineInfoEntity> listNew();

}
