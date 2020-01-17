package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.EquipmentGL1hDTO;
import com.ags.modules.sys.dto.MachineMobilityRateDTO;
import com.ags.modules.sys.dto.MobilityRateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThEuipmentStatusDao;
import com.ags.modules.sys.entity.ThEuipmentStatusEntity;
import com.ags.modules.sys.service.ThEuipmentStatusService;


@Service("thEuipmentStatusService")
public class ThEuipmentStatusServiceImpl extends ServiceImpl<ThEuipmentStatusDao, ThEuipmentStatusEntity> implements ThEuipmentStatusService {

    @Autowired
    private ThEuipmentStatusDao euipmentStatusDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThEuipmentStatusEntity> page = this.page(
                new Query<ThEuipmentStatusEntity>().getPage(params),
                new QueryWrapper<ThEuipmentStatusEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ThEuipmentStatusEntity> listNew() {
        return euipmentStatusDao.listNew();
    }

    @Override
    public List<MobilityRateDTO> queryMobilityByLine() {
        return euipmentStatusDao.queryMobilityByLine();
    }

    @Override
    public List<MachineMobilityRateDTO> queryMobilityByMachine() {
        return euipmentStatusDao.queryMobilityByMachine();
    }

    @Override
    public List<EquipmentGL1hDTO> queryEquipmentGL1h() {
        return euipmentStatusDao.queryEquipmentGL1h();
    }
}
