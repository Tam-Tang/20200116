package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThRateRedDataDao;
import com.ags.modules.sys.entity.ThRateRedDataEntity;
import com.ags.modules.sys.service.ThRateRedDataService;


@Service("thRateRedDataService")
public class ThRateRedDataServiceImpl extends ServiceImpl<ThRateRedDataDao, ThRateRedDataEntity> implements ThRateRedDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThRateRedDataEntity> page = this.page(
                new Query<ThRateRedDataEntity>().getPage(params),
                new QueryWrapper<ThRateRedDataEntity>()
        );

        return new PageUtils(page);
    }

}
