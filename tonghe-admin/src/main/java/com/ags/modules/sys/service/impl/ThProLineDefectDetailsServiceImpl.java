package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThProLineDefectDetailsDao;
import com.ags.modules.sys.entity.ThProLineDefectDetailsEntity;
import com.ags.modules.sys.service.ThProLineDefectDetailsService;


@Service("thProLineDefectDetailsService")
public class ThProLineDefectDetailsServiceImpl extends ServiceImpl<ThProLineDefectDetailsDao, ThProLineDefectDetailsEntity> implements ThProLineDefectDetailsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThProLineDefectDetailsEntity> page = this.page(
                new Query<ThProLineDefectDetailsEntity>().getPage(params),
                new QueryWrapper<ThProLineDefectDetailsEntity>()
        );

        return new PageUtils(page);
    }

}
