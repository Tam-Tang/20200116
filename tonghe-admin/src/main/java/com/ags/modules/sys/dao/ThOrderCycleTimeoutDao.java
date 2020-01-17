package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.OrderCycleTimeOutDTO;
import com.ags.modules.sys.dto.OrderCycleTimeOutPerctDTO;
import com.ags.modules.sys.entity.ThOrderCycleTimeoutEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单生产周期超时记录
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@Mapper
public interface ThOrderCycleTimeoutDao extends BaseMapper<ThOrderCycleTimeoutEntity> {
    List<OrderCycleTimeOutDTO> queryOrderCycleTimeOut();

    OrderCycleTimeOutPerctDTO queryOrderCycleTimeOutPerct();

}
