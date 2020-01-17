package com.ags.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThAutoMachineInfoDao;
import com.ags.modules.sys.entity.ThAutoMachineInfoEntity;
import com.ags.modules.sys.service.ThAutoMachineInfoService;


@Service("thAutoMachineInfoService")
public class ThAutoMachineInfoServiceImpl extends ServiceImpl<ThAutoMachineInfoDao, ThAutoMachineInfoEntity> implements ThAutoMachineInfoService {

    @Autowired
    private ThAutoMachineInfoDao autoMachineInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThAutoMachineInfoEntity> page = this.page(
                new Query<ThAutoMachineInfoEntity>().getPage(params),
                new QueryWrapper<ThAutoMachineInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ThAutoMachineInfoEntity> listNew() {
        return autoMachineInfoDao.listNew();
    }
}
