package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.OrderCycleTimeOutDTO;
import com.ags.modules.sys.dto.OrderCycleTimeOutPerctDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThOrderCycleTimeoutDao;
import com.ags.modules.sys.entity.ThOrderCycleTimeoutEntity;
import com.ags.modules.sys.service.ThOrderCycleTimeoutService;


@Service("thOrderCycleTimeoutService")
public class ThOrderCycleTimeoutServiceImpl extends ServiceImpl<ThOrderCycleTimeoutDao, ThOrderCycleTimeoutEntity> implements ThOrderCycleTimeoutService {

    @Autowired
    private ThOrderCycleTimeoutDao orderCycleTimeoutDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThOrderCycleTimeoutEntity> page = this.page(
                new Query<ThOrderCycleTimeoutEntity>().getPage(params),
                new QueryWrapper<ThOrderCycleTimeoutEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderCycleTimeOutDTO> queryOrderCycleTimeOut() {
        return orderCycleTimeoutDao.queryOrderCycleTimeOut();
    }

    @Override
    public OrderCycleTimeOutPerctDTO queryOrderCycleTimeOutPerct() {
        return orderCycleTimeoutDao.queryOrderCycleTimeOutPerct();
    }
}
