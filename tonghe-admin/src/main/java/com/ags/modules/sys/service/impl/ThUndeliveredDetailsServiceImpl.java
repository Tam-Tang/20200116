package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.UndeliveryPerByQtyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;

import com.ags.modules.sys.dao.ThUndeliveredDetailsDao;
import com.ags.modules.sys.entity.ThUndeliveredDetailsEntity;
import com.ags.modules.sys.service.ThUndeliveredDetailsService;


@Service("thUndeliveredDetailsService")
public class ThUndeliveredDetailsServiceImpl extends ServiceImpl<ThUndeliveredDetailsDao, ThUndeliveredDetailsEntity> implements ThUndeliveredDetailsService {

    @Autowired
    private ThUndeliveredDetailsDao undeliveredDetailsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThUndeliveredDetailsEntity> page = this.page(
                new Query<ThUndeliveredDetailsEntity>().getPage(params),
                new QueryWrapper<ThUndeliveredDetailsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<UndeliveryPerByQtyDTO> queryUndeliveryPerByQty() {
        return undeliveredDetailsDao.queryUndeliveryPerByQty();
    }
}
