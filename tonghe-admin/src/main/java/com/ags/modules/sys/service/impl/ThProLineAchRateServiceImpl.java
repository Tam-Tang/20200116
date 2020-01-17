package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.DataCategoryDTO;
import com.ags.modules.sys.dto.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThProLineAchRateDao;
import com.ags.modules.sys.entity.ThProLineAchRateEntity;
import com.ags.modules.sys.service.ThProLineAchRateService;


@Service("thProLineAchRateService")
public class ThProLineAchRateServiceImpl extends ServiceImpl<ThProLineAchRateDao, ThProLineAchRateEntity> implements ThProLineAchRateService {

    @Autowired
    private ThProLineAchRateDao proLineAchRateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThProLineAchRateEntity> page = this.page(
                new Query<ThProLineAchRateEntity>().getPage(params),
                new QueryWrapper<ThProLineAchRateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DataDTO> queryList(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return proLineAchRateDao.queryList(timeType,timeStart,timeEnd);
    }

    @Override
    public List<DataCategoryDTO> queryListCatrgory(String timeType, String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return proLineAchRateDao.queryListCatrgory(timeType,timeStart,timeEnd);
    }

    @Override
    public String queryTimeRange(String timeType) {

        return proLineAchRateDao.queryTimeRange(timeType);
    }

    @Override
    public DataDTO queryNowWeekTop1NoAch() {
        return proLineAchRateDao.queryNowWeekTop1NoAch();
    }

    @Override
    public DataDTO queryNowWeekTop1Ach() {
        return proLineAchRateDao.queryNowWeekTop1Ach();
    }

    @Override
    public DataDTO queryPreWeekTop1NoAch() {
        return proLineAchRateDao.queryPreWeekTop1NoAch();
    }

    @Override
    public DataDTO queryPreWeekTop1Ach() {
        return proLineAchRateDao.queryPreWeekTop1Ach();
    }
}
