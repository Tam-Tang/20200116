package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThYieldOfProLineDao;
import com.ags.modules.sys.entity.ThYieldOfProLineEntity;
import com.ags.modules.sys.service.ThYieldOfProLineService;


@Service("thYieldOfProLineService")
public class ThYieldOfProLineServiceImpl extends ServiceImpl<ThYieldOfProLineDao, ThYieldOfProLineEntity> implements ThYieldOfProLineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThYieldOfProLineEntity> page = this.page(
                new Query<ThYieldOfProLineEntity>().getPage(params),
                new QueryWrapper<ThYieldOfProLineEntity>()
        );

        return new PageUtils(page);
    }

}
