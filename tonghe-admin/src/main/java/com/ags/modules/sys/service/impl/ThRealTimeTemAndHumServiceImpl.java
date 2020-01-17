package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.HumWarnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThRealTimeTemAndHumDao;
import com.ags.modules.sys.entity.ThRealTimeTemAndHumEntity;
import com.ags.modules.sys.service.ThRealTimeTemAndHumService;


@Service("thRealTimeTemAndHumService")
public class ThRealTimeTemAndHumServiceImpl extends ServiceImpl<ThRealTimeTemAndHumDao, ThRealTimeTemAndHumEntity> implements ThRealTimeTemAndHumService {

    @Autowired
    private ThRealTimeTemAndHumDao realTimeTemAndHumDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThRealTimeTemAndHumEntity> page = this.page(
                new Query<ThRealTimeTemAndHumEntity>().getPage(params),
                new QueryWrapper<ThRealTimeTemAndHumEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public List<ThRealTimeTemAndHumEntity> listNew() {
        return realTimeTemAndHumDao.listNew();
    }

    @Override
    public List<HumWarnDTO> queryTempHumWarn() {
        return realTimeTemAndHumDao.queryTempHumWarn();
    }
}
