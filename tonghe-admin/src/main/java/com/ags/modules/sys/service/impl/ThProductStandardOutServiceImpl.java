package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThProductStandardOutDao;
import com.ags.modules.sys.entity.ThProductStandardOutEntity;
import com.ags.modules.sys.service.ThProductStandardOutService;


@Service("thProductStandardOutService")
public class ThProductStandardOutServiceImpl extends ServiceImpl<ThProductStandardOutDao, ThProductStandardOutEntity> implements ThProductStandardOutService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThProductStandardOutEntity> page = this.page(
                new Query<ThProductStandardOutEntity>().getPage(params),
                new QueryWrapper<ThProductStandardOutEntity>()
        );

        return new PageUtils(page);
    }

}
