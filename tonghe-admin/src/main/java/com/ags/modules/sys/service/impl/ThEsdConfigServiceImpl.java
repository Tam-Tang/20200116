package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThEsdConfigDao;
import com.ags.modules.sys.entity.ThEsdConfigEntity;
import com.ags.modules.sys.service.ThEsdConfigService;


@Service("thEsdConfigService")
public class ThEsdConfigServiceImpl extends ServiceImpl<ThEsdConfigDao, ThEsdConfigEntity> implements ThEsdConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThEsdConfigEntity> page = this.page(
                new Query<ThEsdConfigEntity>().getPage(params),
                new QueryWrapper<ThEsdConfigEntity>()
        );

        return new PageUtils(page);
    }

}
