package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.MaintenanceDetailPieDTO;
import com.ags.modules.sys.vo.MaintenancetrendBrforeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThMaintenanceDetailDao;
import com.ags.modules.sys.entity.ThMaintenanceDetailEntity;
import com.ags.modules.sys.service.ThMaintenanceDetailService;


@Service("thMaintenanceDetailService")
public class ThMaintenanceDetailServiceImpl extends ServiceImpl<ThMaintenanceDetailDao, ThMaintenanceDetailEntity> implements ThMaintenanceDetailService {

    @Autowired
    private ThMaintenanceDetailDao maintenanceDetailDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThMaintenanceDetailEntity> page = this.page(
                new Query<ThMaintenanceDetailEntity>().getPage(params),
                new QueryWrapper<ThMaintenanceDetailEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public MaintenanceDetailPieDTO queryMaintenanceDetailPie(String maintainStep, String timeType) {
        return maintenanceDetailDao.queryMaintenanceDetailPie(maintainStep,timeType);
    }

    @Override
    public List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore4() {
        return maintenanceDetailDao.queryMaintenancetrendBrfore4();
    }

    @Override
    public List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore8() {
        return maintenanceDetailDao.queryMaintenancetrendBrfore8();
    }

    @Override
    public List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore12() {
        return maintenanceDetailDao.queryMaintenancetrendBrfore12();
    }

    @Override
    public List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore16() {
        return maintenanceDetailDao.queryMaintenancetrendBrfore16();
    }

    @Override
    public List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore20() {
        return maintenanceDetailDao.queryMaintenancetrendBrfore20();
    }

    @Override
    public List<MaintenancetrendBrforeVO> queryMaintenancetrendBrfore24() {
        return maintenanceDetailDao.queryMaintenancetrendBrfore24();
    }
}
