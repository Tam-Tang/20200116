package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThStationDao;
import com.ags.modules.sys.entity.ThStationEntity;
import com.ags.modules.sys.service.ThStationService;


@Service("thStationService")
public class ThStationServiceImpl extends ServiceImpl<ThStationDao, ThStationEntity> implements ThStationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThStationEntity> page = this.page(
                new Query<ThStationEntity>().getPage(params),
                new QueryWrapper<ThStationEntity>()
        );

        return new PageUtils(page);
    }

}
