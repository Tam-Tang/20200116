package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dao.ThRealTimeTemAndHumDao;
import com.ags.modules.sys.dto.ESDWarnDTO;
import com.ags.modules.sys.dto.EsdPlaceDTO;
import com.ags.modules.sys.dto.EsdStatusByPlaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThEsdRealTimeDao;
import com.ags.modules.sys.entity.ThEsdRealTimeEntity;
import com.ags.modules.sys.service.ThEsdRealTimeService;


@Service("thEsdRealTimeService")
public class ThEsdRealTimeServiceImpl extends ServiceImpl<ThEsdRealTimeDao, ThEsdRealTimeEntity> implements ThEsdRealTimeService {

    @Autowired
    private ThEsdRealTimeDao esdRealTimeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThEsdRealTimeEntity> page = this.page(
                new Query<ThEsdRealTimeEntity>().getPage(params),
                new QueryWrapper<ThEsdRealTimeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<EsdStatusByPlaceDTO> queryEsdStatusByPlaceMan() {
        return esdRealTimeDao.queryEsdStatusByPlaceMan();
    }

    @Override
    public List<EsdStatusByPlaceDTO> queryEsdStatusByPlace() {
        return esdRealTimeDao.queryEsdStatusByPlace();
    }

    @Override
    public List<EsdStatusByPlaceDTO> queryEsdStatusByPlaceMachine() {
        return esdRealTimeDao.queryEsdStatusByPlaceMachine();
    }


    @Override
    public List<EsdPlaceDTO> queryEsdStatusByPlaceManESD() {
        return esdRealTimeDao.queryEsdStatusByPlaceManESD();
    }

    @Override
    public List<EsdPlaceDTO> queryEsdStatusByPlaceMachineESD() {
        return esdRealTimeDao.queryEsdStatusByPlaceMachineESD();
    }

    @Override
    public List<ESDWarnDTO> queryManESDWarn() {
        return esdRealTimeDao.queryManESDWarn();
    }

    @Override
    public List<ESDWarnDTO> queryMachineESDWarn() {
        return esdRealTimeDao.queryMachineESDWarn();
    }
}
