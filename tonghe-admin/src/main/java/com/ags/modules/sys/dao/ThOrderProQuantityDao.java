package com.ags.modules.sys.dao;

import com.ags.modules.sys.dto.DeliveryByOrderQtyDTO;
import com.ags.modules.sys.entity.ThOrderProQuantityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单交付状况记录表（订单产品数量单位）
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@Mapper
public interface ThOrderProQuantityDao extends BaseMapper<ThOrderProQuantityEntity> {

    public List<DeliveryByOrderQtyDTO> queryDeliveryByOrderQty();
}
