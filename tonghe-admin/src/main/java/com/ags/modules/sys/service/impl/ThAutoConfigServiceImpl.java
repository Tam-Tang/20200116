package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThAutoConfigDao;
import com.ags.modules.sys.entity.ThAutoConfigEntity;
import com.ags.modules.sys.service.ThAutoConfigService;


@Service("thAutoConfigService")
public class ThAutoConfigServiceImpl extends ServiceImpl<ThAutoConfigDao, ThAutoConfigEntity> implements ThAutoConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThAutoConfigEntity> page = this.page(
                new Query<ThAutoConfigEntity>().getPage(params),
                new QueryWrapper<ThAutoConfigEntity>()
        );

        return new PageUtils(page);
    }

}
