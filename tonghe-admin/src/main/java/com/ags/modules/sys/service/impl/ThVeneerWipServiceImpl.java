package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.vo.VeneerWipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThVeneerWipDao;
import com.ags.modules.sys.entity.ThVeneerWipEntity;
import com.ags.modules.sys.service.ThVeneerWipService;


@Service("thVeneerWipService")
public class ThVeneerWipServiceImpl extends ServiceImpl<ThVeneerWipDao, ThVeneerWipEntity> implements ThVeneerWipService {

    @Autowired
    private ThVeneerWipDao veneerWipDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThVeneerWipEntity> page = this.page(
                new Query<ThVeneerWipEntity>().getPage(params),
                new QueryWrapper<ThVeneerWipEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<VeneerWipVO> queryVeneerWip(String timeType) {
        return veneerWipDao.queryVeneerWip(timeType);
    }
}
