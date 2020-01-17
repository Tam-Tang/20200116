package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.DayEachMachineThrowingRateDTO;
import com.ags.modules.sys.dto.DayMachineThrowingRateDTO;
import com.ags.modules.sys.dto.MachineThrowingRateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThMachineThrowingRateDao;
import com.ags.modules.sys.entity.ThMachineThrowingRateEntity;
import com.ags.modules.sys.service.ThMachineThrowingRateService;


@Service("thMachineThrowingRateService")
public class ThMachineThrowingRateServiceImpl extends ServiceImpl<ThMachineThrowingRateDao, ThMachineThrowingRateEntity> implements ThMachineThrowingRateService {

    @Autowired
    private ThMachineThrowingRateDao machineThrowingRateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThMachineThrowingRateEntity> page = this.page(
                new Query<ThMachineThrowingRateEntity>().getPage(params),
                new QueryWrapper<ThMachineThrowingRateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ThMachineThrowingRateEntity> listNew() {
        return machineThrowingRateDao.listNew();
    }

    @Override
    public DayMachineThrowingRateDTO queryDayMachineThrowingRate() {
        return machineThrowingRateDao.queryDayMachineThrowingRate();
    }

    @Override
    public List<DayEachMachineThrowingRateDTO> queryDayEachMachineThrowingRate() {
        return machineThrowingRateDao.queryDayEachMachineThrowingRate();
    }

    @Override
    public List<MachineThrowingRateDTO> queryEachMachineThrowingRateTop3() {
        return machineThrowingRateDao.queryEachMachineThrowingRateTop3();
    }
}
