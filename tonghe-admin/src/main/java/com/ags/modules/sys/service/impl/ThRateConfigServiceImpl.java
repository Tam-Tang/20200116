package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThRateConfigDao;
import com.ags.modules.sys.entity.ThRateConfigEntity;
import com.ags.modules.sys.service.ThRateConfigService;


@Service("thRateConfigService")
public class ThRateConfigServiceImpl extends ServiceImpl<ThRateConfigDao, ThRateConfigEntity> implements ThRateConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThRateConfigEntity> page = this.page(
                new Query<ThRateConfigEntity>().getPage(params),
                new QueryWrapper<ThRateConfigEntity>()
        );

        return new PageUtils(page);
    }

}
