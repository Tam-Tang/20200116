package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThEuipmentConfigDao;
import com.ags.modules.sys.entity.ThEuipmentConfigEntity;
import com.ags.modules.sys.service.ThEuipmentConfigService;


@Service("thEuipmentConfigService")
public class ThEuipmentConfigServiceImpl extends ServiceImpl<ThEuipmentConfigDao, ThEuipmentConfigEntity> implements ThEuipmentConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThEuipmentConfigEntity> page = this.page(
                new Query<ThEuipmentConfigEntity>().getPage(params),
                new QueryWrapper<ThEuipmentConfigEntity>()
        );

        return new PageUtils(page);
    }

}
