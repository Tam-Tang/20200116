package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.DeliveryByOrderDTO;
import com.ags.modules.sys.dto.DeliveryByOrderQtyDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThOrderDeliveryRecordEntity;

import java.util.Map;

/**
 * 订单交付状况记录表（订单单位）
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThOrderDeliveryRecordService extends IService<ThOrderDeliveryRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public DeliveryByOrderDTO queryDeliveryByOrder();



}

