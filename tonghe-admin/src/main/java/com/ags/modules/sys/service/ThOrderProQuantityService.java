package com.ags.modules.sys.service;

import com.ags.modules.sys.dto.DeliveryByOrderQtyDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThOrderProQuantityEntity;
import java.util.List;
import java.util.Map;

/**
 * 订单交付状况记录表（订单产品数量单位）
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
public interface ThOrderProQuantityService extends IService<ThOrderProQuantityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public List<DeliveryByOrderQtyDTO> queryDeliveryByOrderQty();

}

