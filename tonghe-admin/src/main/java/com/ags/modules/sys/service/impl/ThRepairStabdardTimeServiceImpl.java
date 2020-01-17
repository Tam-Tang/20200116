package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThRepairStabdardTimeDao;
import com.ags.modules.sys.entity.ThRepairStabdardTimeEntity;
import com.ags.modules.sys.service.ThRepairStabdardTimeService;


@Service("thRepairStabdardTimeService")
public class ThRepairStabdardTimeServiceImpl extends ServiceImpl<ThRepairStabdardTimeDao, ThRepairStabdardTimeEntity> implements ThRepairStabdardTimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThRepairStabdardTimeEntity> page = this.page(
                new Query<ThRepairStabdardTimeEntity>().getPage(params),
                new QueryWrapper<ThRepairStabdardTimeEntity>()
        );

        return new PageUtils(page);
    }

}
