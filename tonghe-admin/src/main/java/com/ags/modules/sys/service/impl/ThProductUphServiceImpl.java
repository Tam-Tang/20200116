package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThProductUphDao;
import com.ags.modules.sys.entity.ThProductUphEntity;
import com.ags.modules.sys.service.ThProductUphService;


@Service("thProductUphService")
public class ThProductUphServiceImpl extends ServiceImpl<ThProductUphDao, ThProductUphEntity> implements ThProductUphService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThProductUphEntity> page = this.page(
                new Query<ThProductUphEntity>().getPage(params),
                new QueryWrapper<ThProductUphEntity>()
        );

        return new PageUtils(page);
    }

}
