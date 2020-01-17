package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.DeliveryByOrderDTO;
import com.ags.modules.sys.dto.DeliveryByOrderQtyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThOrderDeliveryRecordDao;
import com.ags.modules.sys.entity.ThOrderDeliveryRecordEntity;
import com.ags.modules.sys.service.ThOrderDeliveryRecordService;


@Service("thOrderDeliveryRecordService")
public class ThOrderDeliveryRecordServiceImpl extends ServiceImpl<ThOrderDeliveryRecordDao, ThOrderDeliveryRecordEntity> implements ThOrderDeliveryRecordService {

    @Autowired
    private ThOrderDeliveryRecordDao orderDeliveryRecordDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThOrderDeliveryRecordEntity> page = this.page(
                new Query<ThOrderDeliveryRecordEntity>().getPage(params),
                new QueryWrapper<ThOrderDeliveryRecordEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public DeliveryByOrderDTO queryDeliveryByOrder() {
        return orderDeliveryRecordDao.queryDeliveryByOrder();
    }


}
