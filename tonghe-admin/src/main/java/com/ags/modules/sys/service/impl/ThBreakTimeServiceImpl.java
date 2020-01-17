package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThBreakTimeDao;
import com.ags.modules.sys.entity.ThBreakTimeEntity;
import com.ags.modules.sys.service.ThBreakTimeService;


@Service("thBreakTimeService")
public class ThBreakTimeServiceImpl extends ServiceImpl<ThBreakTimeDao, ThBreakTimeEntity> implements ThBreakTimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThBreakTimeEntity> page = this.page(
                new Query<ThBreakTimeEntity>().getPage(params),
                new QueryWrapper<ThBreakTimeEntity>()
        );

        return new PageUtils(page);
    }

}
