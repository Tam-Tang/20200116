package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThMaintenanceConfigDao;
import com.ags.modules.sys.entity.ThMaintenanceConfigEntity;
import com.ags.modules.sys.service.ThMaintenanceConfigService;


@Service("thMaintenanceConfigService")
public class ThMaintenanceConfigServiceImpl extends ServiceImpl<ThMaintenanceConfigDao, ThMaintenanceConfigEntity> implements ThMaintenanceConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThMaintenanceConfigEntity> page = this.page(
                new Query<ThMaintenanceConfigEntity>().getPage(params),
                new QueryWrapper<ThMaintenanceConfigEntity>()
        );

        return new PageUtils(page);
    }

}
