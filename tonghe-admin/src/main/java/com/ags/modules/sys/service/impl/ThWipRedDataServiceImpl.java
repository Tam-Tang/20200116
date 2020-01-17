package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThWipRedDataDao;
import com.ags.modules.sys.entity.ThWipRedDataEntity;
import com.ags.modules.sys.service.ThWipRedDataService;


@Service("thWipRedDataService")
public class ThWipRedDataServiceImpl extends ServiceImpl<ThWipRedDataDao, ThWipRedDataEntity> implements ThWipRedDataService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThWipRedDataEntity> page = this.page(
                new Query<ThWipRedDataEntity>().getPage(params),
                new QueryWrapper<ThWipRedDataEntity>()
        );

        return new PageUtils(page);
    }

}
