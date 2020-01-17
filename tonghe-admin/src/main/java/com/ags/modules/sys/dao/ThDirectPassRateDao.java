package com.ags.modules.sys.dao;

import com.ags.modules.sys.entity.ThDirectPassRateEntity;
import com.ags.modules.sys.vo.DirectPassRateVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 直通率數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:11
 */
@Mapper
public interface ThDirectPassRateDao extends BaseMapper<ThDirectPassRateEntity> {

    /**
     * 查询PCBA直通率
     * @param timeType
     * @return
     */
    List<DirectPassRateVo> queryPCBADirectPassRare(@Param("timeType")String timeType,@Param("timeStart")String timeStart,@Param("timeEnd")String timeEnd);

    /**
     * 查询CTO直通率
     * @param timeType
     * @return
     */
    List<DirectPassRateVo> queryCTODirectPassRare(@Param("timeType")String timeType,@Param("timeStart")String timeStart,@Param("timeEnd")String timeEnd);
}
