package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThProductLineDao;
import com.ags.modules.sys.entity.ThProductLineEntity;
import com.ags.modules.sys.service.ThProductLineService;


@Service("thProductLineService")
public class ThProductLineServiceImpl extends ServiceImpl<ThProductLineDao, ThProductLineEntity> implements ThProductLineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThProductLineEntity> page = this.page(
                new Query<ThProductLineEntity>().getPage(params),
                new QueryWrapper<ThProductLineEntity>()
        );

        return new PageUtils(page);
    }

}
