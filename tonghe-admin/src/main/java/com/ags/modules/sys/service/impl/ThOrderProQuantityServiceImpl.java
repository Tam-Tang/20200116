package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.DeliveryByOrderQtyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThOrderProQuantityDao;
import com.ags.modules.sys.entity.ThOrderProQuantityEntity;
import com.ags.modules.sys.service.ThOrderProQuantityService;


@Service("thOrderProQuantityService")
public class ThOrderProQuantityServiceImpl extends ServiceImpl<ThOrderProQuantityDao, ThOrderProQuantityEntity> implements ThOrderProQuantityService {

    @Autowired
    private ThOrderProQuantityDao orderProQuantityDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThOrderProQuantityEntity> page = this.page(
                new Query<ThOrderProQuantityEntity>().getPage(params),
                new QueryWrapper<ThOrderProQuantityEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DeliveryByOrderQtyDTO> queryDeliveryByOrderQty() {
        return orderProQuantityDao.queryDeliveryByOrderQty();
    }

}
