package com.ags.modules.sys.service.impl;

import com.ags.modules.sys.dto.BadTypeTop3DTO;
import com.ags.modules.sys.dto.YieldByStationDTO;
import com.ags.modules.sys.dto.YieldTrendByStationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.Query;
import com.ags.modules.sys.dao.ThQualityYieldDao;
import com.ags.modules.sys.entity.ThQualityYieldEntity;
import com.ags.modules.sys.service.ThQualityYieldService;


@Service("thQualityYieldService")
public class ThQualityYieldServiceImpl extends ServiceImpl<ThQualityYieldDao, ThQualityYieldEntity> implements ThQualityYieldService {

    @Autowired
    private ThQualityYieldDao qualityYieldDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ThQualityYieldEntity> page = this.page(
                new Query<ThQualityYieldEntity>().getPage(params),
                new QueryWrapper<ThQualityYieldEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<YieldByStationDTO> queryYieldByStation(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return qualityYieldDao.queryYieldByStation(timeType,timeStart,timeEnd);
    }

    @Override
    public Map<String, YieldByStationDTO> queryStandardYieldByStation() {
        List<YieldByStationDTO> yieldByStationDTOS = qualityYieldDao.queryStandardYieldByStation();
        Map<String, YieldByStationDTO> yieldByStationDTOMap = new HashMap<>();
        for(YieldByStationDTO yieldByStationDTO : yieldByStationDTOS){
            yieldByStationDTOMap.put(yieldByStationDTO.getStationId(),yieldByStationDTO);
        }
        return yieldByStationDTOMap;
    }

    @Override
    public Map<String, Map<String, YieldTrendByStationDTO>> queryStationYieldTrend(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];

        List<YieldTrendByStationDTO> yieldTrendByStationDTOS = qualityYieldDao.queryStationYieldTrend(timeType,timeStart,timeEnd);
        Map<String, Map<String, YieldTrendByStationDTO>>  yieldTrendByStationDTOSMap= new HashMap<>();
        for(YieldTrendByStationDTO yieldTrendByStationDTO : yieldTrendByStationDTOS){
            if(yieldTrendByStationDTOSMap.containsKey(yieldTrendByStationDTO.getStationId())){
                Map<String, YieldTrendByStationDTO> yieldStationTrends = yieldTrendByStationDTOSMap.get(yieldTrendByStationDTO.getStationId());
                yieldTrendByStationDTOSMap.put(yieldTrendByStationDTO.getStationId(),yieldStationTrends);
                //数据返回不会出现说重复相同站别相同time多条数据 所以直接put
                yieldStationTrends.put(yieldTrendByStationDTO.getTime(),yieldTrendByStationDTO);
            }else{
                Map<String, YieldTrendByStationDTO> yieldStationTrends = new HashMap<>();
                yieldStationTrends.put(yieldTrendByStationDTO.getTime(),yieldTrendByStationDTO);
                yieldTrendByStationDTOSMap.put(yieldTrendByStationDTO.getStationId(),yieldStationTrends);
            }
        }
        return yieldTrendByStationDTOSMap;
    }

    @Override
    public List<String> queryTimeByTimeType(String timeType) {
        return qualityYieldDao.queryTimeByTimeType(timeType);
    }

    @Override
    public List<BadTypeTop3DTO> queryBadTypeTop3ByStation(String timeType,String timeRange) {
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        return qualityYieldDao.queryBadTypeTop3ByStation(timeType,timeStart,timeEnd);
    }

    @Override
    public String queryTimeRange(String timeType) {
        return qualityYieldDao.queryTimeRange(timeType);
    }
}
