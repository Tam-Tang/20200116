package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.OrderCycleTimeOutDTO;
import com.ags.modules.sys.dto.OrderCycleTimeOutPerctDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThOrderCycleTimeoutEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单生产周期超时记录
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThOrderCycleTimeoutService extends IService<ThOrderCycleTimeoutEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<OrderCycleTimeOutDTO> queryOrderCycleTimeOut();

    OrderCycleTimeOutPerctDTO queryOrderCycleTimeOutPerct();

}

