package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.vo.TwoRefluxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThTworefluxDao;
import com.ags.modules.sys.entity.ThTworefluxEntity;
import com.ags.modules.sys.service.ThTworefluxService;


@Service("thTworefluxService")
public class ThTworefluxServiceImpl extends ServiceImpl<ThTworefluxDao, ThTworefluxEntity> implements ThTworefluxService {

    @Autowired
    private ThTworefluxDao tworefluxDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThTworefluxEntity> page = this.page(
                new Query<ThTworefluxEntity>().getPage(params),
                new QueryWrapper<ThTworefluxEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<TwoRefluxVO> queryTwoReflux() {
        return tworefluxDao.queryTwoReflux();
    }
}
