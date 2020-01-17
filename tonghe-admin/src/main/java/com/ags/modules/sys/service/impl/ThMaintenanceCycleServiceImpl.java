package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.MaintenanceCycleTop10DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThMaintenanceCycleDao;
import com.ags.modules.sys.entity.ThMaintenanceCycleEntity;
import com.ags.modules.sys.service.ThMaintenanceCycleService;


@Service("thMaintenanceCycleService")
public class ThMaintenanceCycleServiceImpl extends ServiceImpl<ThMaintenanceCycleDao, ThMaintenanceCycleEntity> implements ThMaintenanceCycleService {

    @Autowired
    private ThMaintenanceCycleDao maintenanceCycleDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThMaintenanceCycleEntity> page = this.page(
                new Query<ThMaintenanceCycleEntity>().getPage(params),
                new QueryWrapper<ThMaintenanceCycleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MaintenanceCycleTop10DTO> queryMaintenanceCycleTop10(String maintainStep, String timeType) {
        return maintenanceCycleDao.queryMaintenanceCycleTop10(maintainStep,timeType);
    }
}
