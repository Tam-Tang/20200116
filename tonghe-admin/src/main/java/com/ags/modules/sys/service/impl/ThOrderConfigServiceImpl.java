package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThOrderConfigDao;
import com.ags.modules.sys.entity.ThOrderConfigEntity;
import com.ags.modules.sys.service.ThOrderConfigService;


@Service("thOrderConfigService")
public class ThOrderConfigServiceImpl extends ServiceImpl<ThOrderConfigDao, ThOrderConfigEntity> implements ThOrderConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThOrderConfigEntity> page = this.page(
                new Query<ThOrderConfigEntity>().getPage(params),
                new QueryWrapper<ThOrderConfigEntity>()
        );

        return new PageUtils(page);
    }

}
