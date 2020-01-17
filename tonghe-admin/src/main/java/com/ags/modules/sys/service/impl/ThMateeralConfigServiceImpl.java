package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThMateeralConfigDao;
import com.ags.modules.sys.entity.ThMateeralConfigEntity;
import com.ags.modules.sys.service.ThMateeralConfigService;


@Service("thMateeralConfigService")
public class ThMateeralConfigServiceImpl extends ServiceImpl<ThMateeralConfigDao, ThMateeralConfigEntity> implements ThMateeralConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThMateeralConfigEntity> page = this.page(
                new Query<ThMateeralConfigEntity>().getPage(params),
                new QueryWrapper<ThMateeralConfigEntity>()
        );

        return new PageUtils(page);
    }

}
