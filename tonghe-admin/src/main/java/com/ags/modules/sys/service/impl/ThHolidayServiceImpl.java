package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThHolidayDao;
import com.ags.modules.sys.entity.ThHolidayEntity;
import com.ags.modules.sys.service.ThHolidayService;


@Service("thHolidayService")
public class ThHolidayServiceImpl extends ServiceImpl<ThHolidayDao, ThHolidayEntity> implements ThHolidayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThHolidayEntity> page = this.page(
                new Query<ThHolidayEntity>().getPage(params),
                new QueryWrapper<ThHolidayEntity>()
        );

        return new PageUtils(page);
    }

}
