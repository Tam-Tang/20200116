package com.ags.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThShiftDao;
import com.ags.modules.sys.entity.ThShiftEntity;
import com.ags.modules.sys.service.ThShiftService;


@Service("thShiftService")
public class ThShiftServiceImpl extends ServiceImpl<ThShiftDao, ThShiftEntity> implements ThShiftService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThShiftEntity> page = this.page(
                new Query<ThShiftEntity>().getPage(params),
                new QueryWrapper<ThShiftEntity>()
        );

        return new PageUtils(page);
    }

}
