package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThBadTypeDao;
import com.ags.modules.sys.entity.ThBadTypeEntity;
import com.ags.modules.sys.service.ThBadTypeService;


@Service("thBadTypeService")
public class ThBadTypeServiceImpl extends ServiceImpl<ThBadTypeDao, ThBadTypeEntity> implements ThBadTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThBadTypeEntity> page = this.page(
                new Query<ThBadTypeEntity>().getPage(params),
                new QueryWrapper<ThBadTypeEntity>()
        );

        return new PageUtils(page);
    }

}
