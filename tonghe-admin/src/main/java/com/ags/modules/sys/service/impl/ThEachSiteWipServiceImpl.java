package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.EachSiteWipDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThEachSiteWipDao;
import com.ags.modules.sys.entity.ThEachSiteWipEntity;
import com.ags.modules.sys.service.ThEachSiteWipService;


@Service("thEachSiteWipService")
public class ThEachSiteWipServiceImpl extends ServiceImpl<ThEachSiteWipDao, ThEachSiteWipEntity> implements ThEachSiteWipService {

    @Autowired
    private ThEachSiteWipDao eachSiteWipDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThEachSiteWipEntity> page = this.page(
                new Query<ThEachSiteWipEntity>().getPage(params),
                new QueryWrapper<ThEachSiteWipEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<EachSiteWipDataDTO> queryList(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return eachSiteWipDao.queryList(timeType,timeStart,timeEnd);
    }
}
