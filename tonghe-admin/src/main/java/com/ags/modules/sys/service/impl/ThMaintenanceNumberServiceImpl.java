package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.MaintenanceNumberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThMaintenanceNumberDao;
import com.ags.modules.sys.entity.ThMaintenanceNumberEntity;
import com.ags.modules.sys.service.ThMaintenanceNumberService;


@Service("thMaintenanceNumberService")
public class ThMaintenanceNumberServiceImpl extends ServiceImpl<ThMaintenanceNumberDao, ThMaintenanceNumberEntity> implements ThMaintenanceNumberService {

    @Autowired
    private ThMaintenanceNumberDao maintenanceNumberDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThMaintenanceNumberEntity> page = this.page(
                new Query<ThMaintenanceNumberEntity>().getPage(params),
                new QueryWrapper<ThMaintenanceNumberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MaintenanceNumberDTO queryMaintenanceNumber(String timeType) {
        return maintenanceNumberDao.queryMaintenanceNumber(timeType);
    }
}
