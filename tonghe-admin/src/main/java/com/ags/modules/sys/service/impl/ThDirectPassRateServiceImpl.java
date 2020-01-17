package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.vo.DirectPassRateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;
import com.ags.modules.sys.dao.ThDirectPassRateDao;
import com.ags.modules.sys.entity.ThDirectPassRateEntity;
import com.ags.modules.sys.service.ThDirectPassRateService;


@Service("thDirectPassRateService")
public class ThDirectPassRateServiceImpl extends ServiceImpl<ThDirectPassRateDao, ThDirectPassRateEntity> implements ThDirectPassRateService {

    @Autowired
    private ThDirectPassRateDao directPassRateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThDirectPassRateEntity> page = this.page(
                new Query<ThDirectPassRateEntity>().getPage(params),
                new QueryWrapper<ThDirectPassRateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DirectPassRateVo> queryPCBADirectPassRare(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return directPassRateDao.queryPCBADirectPassRare(timeType,timeStart,timeEnd);
    }

    @Override
    public List<DirectPassRateVo> queryCTODirectPassRare(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return directPassRateDao.queryCTODirectPassRare(timeType,timeStart,timeEnd);
    }
}
