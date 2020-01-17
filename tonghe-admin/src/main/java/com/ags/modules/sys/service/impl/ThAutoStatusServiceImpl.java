package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dao.ThEuipmentStatusDao;
import com.ags.modules.sys.dto.AutoStatusDTO;
import com.ags.modules.sys.dto.AutoStatusWarnDTO;
import com.ags.modules.sys.entity.ThEuipmentStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThAutoStatusDao;
import com.ags.modules.sys.entity.ThAutoStatusEntity;
import com.ags.modules.sys.service.ThAutoStatusService;


@Service("thAutoStatusService")
public class ThAutoStatusServiceImpl extends ServiceImpl<ThAutoStatusDao, ThAutoStatusEntity> implements ThAutoStatusService {

    @Autowired
    private ThAutoStatusDao autoStatusDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThAutoStatusEntity> page = this.page(
                new Query<ThAutoStatusEntity>().getPage(params),
                new QueryWrapper<ThAutoStatusEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ThAutoStatusEntity> listNew() {
        return autoStatusDao.listNew();
    }

    @Override
    public List<AutoStatusDTO> queryStatusBylistNew() {
        return autoStatusDao.queryStatusBylistNew();
    }

    @Override
    public List<AutoStatusWarnDTO> queryWarn5BylistNew() {
        return autoStatusDao.queryWarn5BylistNew();
    }
}
