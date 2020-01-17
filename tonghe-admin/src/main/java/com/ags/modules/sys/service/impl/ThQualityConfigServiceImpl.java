package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThQualityConfigDao;
import com.ags.modules.sys.entity.ThQualityConfigEntity;
import com.ags.modules.sys.service.ThQualityConfigService;


@Service("thQualityConfigService")
public class ThQualityConfigServiceImpl extends ServiceImpl<ThQualityConfigDao, ThQualityConfigEntity> implements ThQualityConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThQualityConfigEntity> page = this.page(
                new Query<ThQualityConfigEntity>().getPage(params),
                new QueryWrapper<ThQualityConfigEntity>()
        );

        return new PageUtils(page);
    }

}
