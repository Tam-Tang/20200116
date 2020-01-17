package com.ags.modules.sys.controller;

import com.ags.common.config.MyConstants;
import com.ags.common.utils.R;
import com.ags.modules.sys.dto.*;
import com.ags.modules.sys.entity.*;
import com.ags.modules.sys.service.*;
import com.ags.modules.sys.vo.*;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.scene.chart.PieChart;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 看板控制器
 * @author LancCJ
 */
@RestController
@RequestMapping("/board")
@Api(value = "统合看板接口",tags  = "统合看板接口")
public class BoardController {

    @Autowired
    private MyConstants myConstants;

    @Autowired
    private JavaMailSenderImpl mailSender;
    /**
     * 各站点WIP
     */
    @Autowired
    private ThEachSiteWipService eachSiteWipService;

    /**
     * 产线达成率
     */
    @Autowired
    private ThProLineAchRateService proLineAchRateService;

    /**
     * 产线良率表
     */
    @Autowired
    private ThYieldOfProLineService yieldOfProLineService;

    /**
     * 产线不良明细表
     */
    @Autowired
    private ThProLineDefectDetailsService proLineDefectDetailsService;

    /**
     * 自动化设备装填
     */
    @Autowired
    private ThAutoStatusService autoStatusService;

    /**
     * 抛料
     */
    @Autowired
    private ThMachineThrowingRateService machineThrowingRateService;

    /**
     * 设备运行状态(工序)
     */
    @Autowired
    private ThEuipmentStatusService euipmentStatusService;

    /**
     * 温湿度
     */
    @Autowired
    private ThRealTimeTemAndHumService realTimeTemAndHumService;

    /**
     * ESD
     */
    @Autowired
    private ThEsdRealTimeService esdRealTimeService;

    /**
     * 站点良率
     */
    @Autowired
    private ThQualityYieldService qualityYieldService;

    @Autowired
    private ThOrderDeliveryRecordService orderDeliveryRecordService;

    @Autowired
    private ThOrderProQuantityService orderProQuantityService;

    @Autowired
    private ThUndeliveredDetailsService undeliveredDetailsService;

    @Autowired
    private ThMaintenanceNumberService maintenanceNumberService;

    @Autowired
    private ThOrderCycleTimeoutService orderCycleTimeoutService;

    //维修

    @Autowired
    private ThMaintenanceCycleService maintenanceCycleService;

    @Autowired
    private ThMaintenanceDetailService maintenanceDetailService;

    @Autowired
    private ThQualityConfigService qualityConfigService;

    @Autowired
    private ThDirectPassRateService directPassRateService;

    SwingWorker<String, Object> task =null;

    /**
     * 质量看板接口
     * @param timeType 传入参数名:timetype,参数值：今日dd本周iw本月mm
     * @return
     */
    @GetMapping("/quality")
    @ApiOperation(value = "质量看板接口",produces = "application/json")
    public R quality(@ApiParam(name="timeType") @RequestParam(defaultValue = "dd")  String timeType){

        //查询周期数据

        String timeRange = qualityYieldService.queryTimeRange(timeType);
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        DataRangeTimeVO dataRangeTimeVO = new DataRangeTimeVO(timeStart,timeEnd);

        //查询站点平均良率
        List<YieldByStationDTO> yieldByStationDTOS= qualityYieldService.queryYieldByStation(timeType,timeRange);
        //查询标准平均良率信息  没有的设置为 0
        Map<String, YieldByStationDTO> yieldByStationDTOMap = qualityYieldService.queryStandardYieldByStation();
        //组合
        List<YieldByStationVO> yieldByStationVOS = Lists.newArrayList();
        for(YieldByStationDTO yieldByStationDTO : yieldByStationDTOS){
            YieldByStationVO yieldByStationVO = new YieldByStationVO();
            BeanUtils.copyProperties(yieldByStationDTO,yieldByStationVO);
            //有标准值就获取 没有就为0
            String starndardYield = ((YieldByStationDTO)yieldByStationDTOMap.get(yieldByStationDTO.getStationId()))!=null?((YieldByStationDTO)yieldByStationDTOMap.get(yieldByStationDTO.getStationId())).getYield():"0";
            yieldByStationVO.setStarndardYield(starndardYield);
            yieldByStationVOS.add(yieldByStationVO);
        }

        //查询各工序不良TOP3
        //先查各个产线站点的良率趋势图
        Map<String,Map<String, YieldTrendByStationDTO>> yieldTrendByStationMap= qualityYieldService.queryStationYieldTrend(timeType,timeRange);
        //通过timeType把相应的时间数据获取到  排序 从小到大
        List<String> times = qualityYieldService.queryTimeByTimeType(timeType);
        List<YeildTrendVO> yeildTrendVOS = Lists.newArrayList();

        //按照站查询TOP3不良分类
        List<BadTypeTop3DTO> badTypeTop3DTOS = qualityYieldService.queryBadTypeTop3ByStation(timeType,timeRange);

        //组装右侧的TOP3不良分类 和  趋势数据
        List<YieldTrendAndTop3VO> yieldTrendAndTop3VOS = Lists.newArrayList();
        for(BadTypeTop3DTO badTypeTop3DTO : badTypeTop3DTOS){
            YieldTrendAndTop3VO yieldTrendAndTop3VO = new YieldTrendAndTop3VO();

            yieldTrendAndTop3VO.setStationId(badTypeTop3DTO.getStationId());
            yieldTrendAndTop3VO.setStationName(badTypeTop3DTO.getStationName());

            //添加趋势数据
            YeildTrendVO yeildTrendVO = new YeildTrendVO();
            String[] yieldTrends = new String[times.size()];
            String[] yieldTrendTimes = new String[times.size()];
            for (int i = 0; i < times.size(); i++) {
                if(yieldTrendByStationMap.size()>0){

                    try{
                        String yieldTrend = yieldTrendByStationMap.get(badTypeTop3DTO.getStationId()).get(times.get(i))==null?"0":yieldTrendByStationMap.get(badTypeTop3DTO.getStationId()).get(times.get(i)).getYield();
                        yieldTrends[i] = yieldTrend;
                        yieldTrendTimes[i] = times.get(i);
                    }catch (Exception e){
                        yieldTrends[i] = "0";
                    }


                }else{
                    try{
                    String yieldTrend = yieldTrendByStationMap.get(badTypeTop3DTO.getStationId()).get(times.get(i))==null?"0":yieldTrendByStationMap.get(badTypeTop3DTO.getStationId()).get(times.get(i)).getYield();
                    yieldTrends[i] = yieldTrend;
                    yieldTrendTimes[i] = times.get(i);
                    }catch (Exception e){
                        yieldTrends[i] = "0";
                    }
                }
            }
            yeildTrendVO.setYields(yieldTrends);
            yeildTrendVO.setTimes(yieldTrendTimes);

            yieldTrendAndTop3VO.setYeildTrend(yeildTrendVO);

            //添加TOP3不良分类数据
            List<BadTypeVO> badTypeVOS = Lists.newArrayList();
            for (BadTypeTop3DTO badTypeTop3DTO1 : badTypeTop3DTOS){
                if(badTypeTop3DTO.getStationId().equals(badTypeTop3DTO1.getStationId())){
                    BadTypeVO badTypeVO = new BadTypeVO();
                    BeanUtils.copyProperties(badTypeTop3DTO1,badTypeVO);
                    badTypeVOS.add(badTypeVO);
                }
            }
            yieldTrendAndTop3VO.setBadTypeTop3(badTypeVOS.toArray(new BadTypeVO[badTypeVOS.size()]));

            yieldTrendAndTop3VOS.add(yieldTrendAndTop3VO);
        }


        //改造下数据结构
        List<YieldPieTrendAndTop3VO> yieldPieTrendAndTop3VOS = Lists.newArrayList();
        for (YieldTrendAndTop3VO yieldTrendAndTop3VO : yieldTrendAndTop3VOS){

            yieldTrendAndTop3VO.setStationName(ZhConverterUtil.convertToSimple(yieldTrendAndTop3VO.getStationName()));


            YieldPieTrendAndTop3VO yieldPieTrendAndTop3VO = new YieldPieTrendAndTop3VO();
            //饼图对象
            PieChartVO pieChart = new PieChartVO();
            pieChart.setTitleText(yieldTrendAndTop3VO.getStationName());
            List<String> legendData = Lists.newArrayList();
            List<BigDecimal> seriesData = Lists.newArrayList();
            for (BadTypeVO badTypeVO : yieldTrendAndTop3VO.getBadTypeTop3()){
                legendData.add(badTypeVO.getBadTypeName());
                seriesData.add(new BigDecimal(badTypeVO.getPercent()));
            }
            pieChart.setLegendData(legendData);
            pieChart.setSeriesData(seriesData);
            yieldPieTrendAndTop3VO.setPieChart(pieChart);
            //趋势图对象
            LineChartVO lineChart = new LineChartVO();
            lineChart.setTitleText("良率趋势图");
            YeildTrendVO yeildTrendVO = yieldTrendAndTop3VO.getYeildTrend();
            lineChart.setXaxis(Arrays.asList(yeildTrendVO.getTimes()));
            List<String> SeriesData = Arrays.asList(yeildTrendVO.getYields());
            List<BigDecimal> trendSeriesData = Lists.newArrayList();
            for (String s : SeriesData){
                trendSeriesData.add(new BigDecimal(s));
            }
            lineChart.setSeriesData(trendSeriesData);
            yieldPieTrendAndTop3VO.setLineChart(lineChart);
            yieldPieTrendAndTop3VOS.add(yieldPieTrendAndTop3VO);
        }

        //看板配置
        List<ThQualityConfigEntity> qualityConfigEntities = qualityConfigService.list();

        //查询PCBA直通率
        List<DirectPassRateVo> PCBAdirectPassRateVos = directPassRateService.queryPCBADirectPassRare(timeType,timeRange);
        //数据结构改造
        PcbaDirectPassRateVO pcbaDirectPassRateVO =new PcbaDirectPassRateVO();
        pcbaDirectPassRateVO.setTitle("PCBA直通率");
        List<String> xaxisList = Lists.newLinkedList();
        List<BigDecimal> goalDatas = Lists.newLinkedList();
        List<BigDecimal> trueDatas = Lists.newLinkedList();
        for(DirectPassRateVo directPassRateVo : PCBAdirectPassRateVos){
            xaxisList.add(directPassRateVo.getTime());
            goalDatas.add(new BigDecimal(qualityConfigEntities.get(0).getPcbaStandardData()));
            trueDatas.add(new BigDecimal(directPassRateVo.getPercent()));
        }
        pcbaDirectPassRateVO.setXaxis(xaxisList);
        pcbaDirectPassRateVO.setGoalData(goalDatas);
        pcbaDirectPassRateVO.setTrueData(trueDatas);

        //查询CTO直通率
        List<DirectPassRateVo> CTOdirectPassRateVos = directPassRateService.queryCTODirectPassRare(timeType,timeRange);
        //数据改造
        CtoDirectPassRateVO ctoDirectPassRateVO =new CtoDirectPassRateVO();
        ctoDirectPassRateVO.setTitle("CTO直通率");
        List<String> ctoxaxisList = Lists.newLinkedList();
        List<BigDecimal> ctogoalDatas = Lists.newLinkedList();
        List<BigDecimal> ctotrueDatas = Lists.newLinkedList();
        for(DirectPassRateVo directPassRateVo : CTOdirectPassRateVos){
            ctoxaxisList.add(directPassRateVo.getTime());
            ctogoalDatas.add(new BigDecimal(qualityConfigEntities.get(0).getPcbaStandardData()));
            ctotrueDatas.add(new BigDecimal(directPassRateVo.getPercent()));
        }
        ctoDirectPassRateVO.setXaxis(ctoxaxisList);
        ctoDirectPassRateVO.setGoalData(ctogoalDatas);
        ctoDirectPassRateVO.setTrueData(ctotrueDatas);

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "datarangetime为查询周期," +
                "yields为产线良率数据," +
                "yieldTrendAndTop3为超期TOP10," +
                "pcbaDirectPassRate为PCBA直通率" +
                "ctoDirectPassRate为CTO直通率" +
                "boardconfig为看板配置数据" +
                "}");
        data.put("datarangetime", dataRangeTimeVO);//周期
        data.put("yields", yieldByStationVOS);//左侧站点的良率
        data.put("yieldTrendAndTop3", yieldPieTrendAndTop3VOS);//右侧的TOP3不良分类 和  趋势数据
        data.put("pcbaDirectPassRate", pcbaDirectPassRateVO);
        data.put("ctoDirectPassRate", ctoDirectPassRateVO);
        data.put("boardconfig", qualityConfigEntities.get(0));


        return R.ok()
                .put("data", data)
                ;
    }

    @Autowired
    private ThEuipmentConfigService euipmentConfigService;

    /**
     * 工序看板接口
     * @return
     */
    @GetMapping("/euipment")
    @ApiOperation(value = "工序看板接口",produces = "application/json")
    public R euipment(){

        //查询每台设备的状态(最新的一组数据)
        List<ThEuipmentStatusEntity> euipmentStatusEntityList=euipmentStatusService.listNew();
        for(ThEuipmentStatusEntity thEuipmentStatusEntity : euipmentStatusEntityList){
            thEuipmentStatusEntity.setEuipmentName(ZhConverterUtil.convertToSimple(thEuipmentStatusEntity.getEuipmentName()));
            thEuipmentStatusEntity.setEuipmentType(ZhConverterUtil.convertToSimple(thEuipmentStatusEntity.getEuipmentType()));
        }

        //稼动率  分产线 和  设备的
        List<MobilityRateDTO> mobilityRateDTO = euipmentStatusService.queryMobilityByLine();
        for(MobilityRateDTO mobilityRateDTO1 : mobilityRateDTO){
            mobilityRateDTO1.setLineName(ZhConverterUtil.convertToSimple(mobilityRateDTO1.getLineName()));
        }

        List<MachineMobilityRateDTO> machineMobilityRateDTO = euipmentStatusService.queryMobilityByMachine();
        for(MachineMobilityRateDTO machineMobilityRateDTO1:machineMobilityRateDTO){
            machineMobilityRateDTO1.setEuipmentName(ZhConverterUtil.convertToSimple(machineMobilityRateDTO1.getEuipmentName()));
        }

        //看板配置
         List<ThEuipmentConfigEntity> euipmentConfigEntities= euipmentConfigService.list();

        LinkedHashMap<String,Object> data = new LinkedHashMap();

        data.put("description", "{" +
                "euipmentsStateListData为工序状态," +
                "utilization为线别稼动率," +
                "machineMobilityRate为设备稼动率" +
                "boardconfig为看板配置数据" +
                "}");

        data.put("euipmentsStateListData", euipmentStatusEntityList);
        data.put("utilization", mobilityRateDTO);
        data.put("machineMobilityRate", machineMobilityRateDTO);
        data.put("boardconfig", euipmentConfigEntities.get(0));

        sendEmail("euipment",euipmentConfigEntities.get(0).getEmail().split(";"),euipmentConfigEntities.get(0));

        return R.ok()
                .put("data", data)
                ;

    }

    @Autowired
    private ThAutoConfigService autoConfigService;

    @Autowired
    private ThAutoMachineInfoService autoMachineInfoService;

    /**
     * 自动化看板
     * @return
     */
    @GetMapping("/auto")
    @ApiOperation(value = "自动化看板接口",produces = "application/json")
    public R auto(){

        //查询每台设备的状态(最新的一组数据)
        List<ThAutoStatusEntity> euipmentStatusEntityList=autoStatusService.listNew();

        //PieChartVO machineStatusData = getMachineStatusData(euipmentStatusEntityList);

        //饼图数据
        int okNum = 0; //正常  运行
        int waitNum = 0;//待机
        int stopNum = 0; //停机
        int offlineNum = 0;//离线
        int failNum = 0; // 异常
        for(ThAutoStatusEntity autoStatusEntity : euipmentStatusEntityList){
            //将名称转为简体
            autoStatusEntity.setEuipmentName(ZhConverterUtil.convertToSimple(autoStatusEntity.getEuipmentName()));
            autoStatusEntity.setEuipmentType(ZhConverterUtil.convertToSimple(autoStatusEntity.getEuipmentType()));
//            if(autoStatusEntity.getStatus().equalsIgnoreCase("OK")){
//                okNum+=1;
//            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("WAIT")){
//                waitNum+=1;
//            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("STOP")){
//                stopNum+=1;
//            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("FAIL")){
//                failNum+=1;
//            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("OFFLINE")){
//                offlineNum+=1;
//            }
            if(autoStatusEntity.getStatus().equalsIgnoreCase("1")){
                okNum+=1;
            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("2")){
                waitNum+=1;
            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("3")){
                stopNum+=1;
            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("4")){
                failNum+=1;
            }else if(autoStatusEntity.getStatus().equalsIgnoreCase("5")){
                offlineNum+=1;
            }
        }
        EuipmentsStatePieData euipmentsStatePieData = new EuipmentsStatePieData(String.valueOf(okNum),String.valueOf(waitNum),String.valueOf(failNum),String.valueOf(offlineNum),String.valueOf(stopNum));

        //总稼动率  计算最新一批数据的稼动率
        BigDecimal totalStopTimeHour = new BigDecimal(0);
        for(ThAutoStatusEntity thAutoStatusEntity : euipmentStatusEntityList){
            thAutoStatusEntity.setEuipmentName(ZhConverterUtil.convertToSimple(thAutoStatusEntity.getEuipmentName()));
            thAutoStatusEntity.setEuipmentType(ZhConverterUtil.convertToSimple(thAutoStatusEntity.getEuipmentType()));
            totalStopTimeHour = totalStopTimeHour.add(new BigDecimal(thAutoStatusEntity.getStopTime()));
        }
        //计算平均稼动率
        BigDecimal averageCropMobility =  (new BigDecimal(100)).multiply((new BigDecimal(24)).subtract(totalStopTimeHour.divide(new BigDecimal(euipmentStatusEntityList.size()), 1, BigDecimal.ROUND_HALF_UP)).divide(new BigDecimal(24),1, BigDecimal.ROUND_HALF_UP));

        //设备运行数据
         List<ThAutoMachineInfoEntity> autoMachineInfoEntities= autoMachineInfoService.listNew();
         for(ThAutoMachineInfoEntity thAutoMachineInfoEntity : autoMachineInfoEntities){
             thAutoMachineInfoEntity.setMachineName(ZhConverterUtil.convertToSimple(thAutoMachineInfoEntity.getMachineName()));
             thAutoMachineInfoEntity.setOutputDisplay(ZhConverterUtil.convertToSimple(thAutoMachineInfoEntity.getOutputDisplay()));
             thAutoMachineInfoEntity.setType(ZhConverterUtil.convertToSimple(thAutoMachineInfoEntity.getType()));
             thAutoMachineInfoEntity.setMachinStatusDisplay(ZhConverterUtil.convertToSimple(thAutoMachineInfoEntity.getMachinStatusDisplay()));
             thAutoMachineInfoEntity.setPosition(ZhConverterUtil.convertToSimple(thAutoMachineInfoEntity.getPosition()));
         }

        //看板配置
        List<ThAutoConfigEntity> autoConfigEntities= autoConfigService.list();

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "euipmentsStatePieData为设备运行情况," +
                "averageCropMobility为稼动率," +
                "euipmentsStateListData为数据报警数据TOP5" +
                "autoMachineInfos为设备运行数据" +
                "boardconfig为看板配置数据" +
                "}");
        data.put("euipmentsStatePieData", euipmentsStatePieData);//设备运行情况
        data.put("averageCropMobility", averageCropMobility);//稼动率
        data.put("euipmentsStateListData", euipmentStatusEntityList);//数据报警数据TOP5
        data.put("autoMachineInfos", autoMachineInfoEntities);//设备运行数据
        data.put("boardconfig", autoConfigEntities.get(0));//看板配置信息

        sendEmail("auto",autoConfigEntities.get(0).getEmail().split(";"),autoConfigEntities.get(0));

        return R.ok()
                .put("data", data)
                ;

    }

    @Autowired
    private ThMateeralConfigService mateeralConfigService;

    private List<DayEachMachineThrowingRateVO> dayEachMachineThrowingRateVOS;
    /**
     * 物料 抛料率接口
     * @return
     */
    @GetMapping("/mateeral")
    @ApiOperation(value = "物料(抛料率)接口",produces = "application/json")
    public R mateeral(){

        //计算每天抛料率
        DayMachineThrowingRateDTO dayMachineThrowingRate = machineThrowingRateService.queryDayMachineThrowingRate();

        //计算每台机器的抛料率
        List<DayEachMachineThrowingRateDTO> eachMachineThrowingRateList = machineThrowingRateService.queryDayEachMachineThrowingRate();
        for(DayEachMachineThrowingRateDTO dayEachMachineThrowingRateDTO : eachMachineThrowingRateList){
            dayEachMachineThrowingRateDTO.setMachineNo(ZhConverterUtil.convertToSimple(dayEachMachineThrowingRateDTO.getMachineNo()));
        }
        //每个几台 还有 TOP3
        List<MachineThrowingRateDTO> machineThrowingRateEntityList = machineThrowingRateService.queryEachMachineThrowingRateTop3();
        for(MachineThrowingRateDTO machineThrowingRateDTO : machineThrowingRateEntityList){
            machineThrowingRateDTO.setMachineNo(ZhConverterUtil.convertToSimple(machineThrowingRateDTO.getMachineNo()));
        }
        Map<String,List<MachineThrowingRateDTO>> top3 = new HashMap<>();
        //拿到按照机器分组的top3数据
        for(MachineThrowingRateDTO thMachineThrowingRateEntity : machineThrowingRateEntityList){
            if(top3.get(thMachineThrowingRateEntity.getMachineNo())!=null){
                //往对象放置
                List<MachineThrowingRateDTO> machineThrowingRateEntities = top3.get(thMachineThrowingRateEntity.getMachineNo());
                machineThrowingRateEntities.add(thMachineThrowingRateEntity);
                top3.put(thMachineThrowingRateEntity.getMachineNo(),machineThrowingRateEntities);
            }else{
                List<MachineThrowingRateDTO> machineThrowingRateEntities = Lists.newArrayList();
                machineThrowingRateEntities.add(thMachineThrowingRateEntity);
                top3.put(thMachineThrowingRateEntity.getMachineNo(),machineThrowingRateEntities);
            }
        }

        //循环设置到VO对象
        dayEachMachineThrowingRateVOS = Lists.newArrayList();
        for(DayEachMachineThrowingRateDTO eachMachineThrowingRateDTO:eachMachineThrowingRateList){
            DayEachMachineThrowingRateVO dayEachMachineThrowingRateVO = new DayEachMachineThrowingRateVO();

            BeanUtils.copyProperties(eachMachineThrowingRateDTO,dayEachMachineThrowingRateVO);
            //设置TOP3
            dayEachMachineThrowingRateVO.setTop3(top3.get(dayEachMachineThrowingRateVO.getMachineNo()));
            dayEachMachineThrowingRateVOS.add(dayEachMachineThrowingRateVO);
        }

        //看板配置
         List<ThMateeralConfigEntity> mateeralConfigEntities = mateeralConfigService.list();

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "dayMachineThrowingRate为天抛料数据," +
                "dayEachMachineThrowingRate为产线抛料率," +
                "boardconfig为看板配置数据" +
                "}");
        data.put("dayMachineThrowingRate", dayMachineThrowingRate);
        data.put("dayEachMachineThrowingRate", dayEachMachineThrowingRateVOS);
        data.put("boardconfig", mateeralConfigEntities.get(0));

        sendEmail("mateeral",mateeralConfigEntities.get(0).getEmail().split(";"),mateeralConfigEntities.get(0));

        return R.ok()
                .put("data", data)
                ;

    }

    @Autowired
    private ThEsdConfigService esdConfigService;

    /**
     *温湿度ESD看板接口
     * @return
     */
    @GetMapping("/temperatureesd")
    @ApiOperation(value = "温湿度ESD接口",produces = "application/json")
    public R temperatureesd(){

        //温度和湿度
        List<ThRealTimeTemAndHumEntity> realTimeTemAndHumEntityList= realTimeTemAndHumService.listNew();
        for(ThRealTimeTemAndHumEntity thRealTimeTemAndHumEntity : realTimeTemAndHumEntityList){
            thRealTimeTemAndHumEntity.setPlace(ZhConverterUtil.convertToSimple(thRealTimeTemAndHumEntity.getPlace()));
        }

        //ESD 人的
        List<EsdStatusByPlaceDTO> esdStatusByPlaceManDTOS = esdRealTimeService.queryEsdStatusByPlaceMan();
        for(EsdStatusByPlaceDTO esdStatusByPlaceDTO : esdStatusByPlaceManDTOS){
            esdStatusByPlaceDTO.setPlace(ZhConverterUtil.convertToSimple(esdStatusByPlaceDTO.getPlace()));
        }
        //人的失败的 ESD信息
        List<EsdPlaceDTO>  esdPlaceManDTOS = esdRealTimeService.queryEsdStatusByPlaceManESD();
        for (EsdPlaceDTO esdPlaceDTO : esdPlaceManDTOS){
            esdPlaceDTO.setPlace(ZhConverterUtil.convertToSimple(esdPlaceDTO.getPlace()));
        }
        Map<String,List<EsdPlaceDTO>> esdPlaceManMap = new HashMap<>();
        for(EsdPlaceDTO esdPlaceDTO:esdPlaceManDTOS){
            if(esdPlaceManMap.containsKey(esdPlaceDTO.getPlace())){
                List<EsdPlaceDTO> esdPlaceDTOS = esdPlaceManMap.get(esdPlaceDTO.getPlace());
                esdPlaceDTOS.add(esdPlaceDTO);
                esdPlaceManMap.put(esdPlaceDTO.getPlace(),esdPlaceDTOS);
            }else{
                List<EsdPlaceDTO> esdPlaceDTOS = Lists.newArrayList();
                esdPlaceDTOS.add(esdPlaceDTO);
                esdPlaceManMap.put(esdPlaceDTO.getPlace(),esdPlaceDTOS);
            }
        }
        for(EsdStatusByPlaceDTO esdStatusByPlaceDTO : esdStatusByPlaceManDTOS){
            if(esdPlaceManMap.containsKey(esdStatusByPlaceDTO.getPlace())){
                esdStatusByPlaceDTO.setFailEsd(esdPlaceManMap.get(esdStatusByPlaceDTO.getPlace()));
            }
        }

        //ESD 机器
        List<EsdStatusByPlaceDTO> esdStatusByPlaceMachineDTOS = esdRealTimeService.queryEsdStatusByPlaceMachine();
        for(EsdStatusByPlaceDTO esdStatusByPlaceDTO : esdStatusByPlaceMachineDTOS){
            esdStatusByPlaceDTO.setPlace(ZhConverterUtil.convertToSimple(esdStatusByPlaceDTO.getPlace()));
        }

        //设备的失败的 ESD信息
        List<EsdPlaceDTO>  esdPlaceMachineDTOS = esdRealTimeService.queryEsdStatusByPlaceMachineESD();
        for(EsdPlaceDTO esdPlaceDTO : esdPlaceMachineDTOS){
            esdPlaceDTO.setPlace(ZhConverterUtil.convertToSimple(esdPlaceDTO.getPlace()));
        }
        Map<String,List<EsdPlaceDTO>> esdPlaceMachineMap = new HashMap<>();
        for(EsdPlaceDTO esdPlaceDTO:esdPlaceMachineDTOS){
            if(esdPlaceMachineMap.containsKey(esdPlaceDTO.getPlace())){
                List<EsdPlaceDTO> esdPlaceDTOS = esdPlaceMachineMap.get(esdPlaceDTO.getPlace());
                esdPlaceDTOS.add(esdPlaceDTO);
                esdPlaceMachineMap.put(esdPlaceDTO.getPlace(),esdPlaceDTOS);
            }else{
                List<EsdPlaceDTO> esdPlaceDTOS = Lists.newArrayList();
                esdPlaceDTOS.add(esdPlaceDTO);
                esdPlaceMachineMap.put(esdPlaceDTO.getPlace(),esdPlaceDTOS);
            }
        }
        for(EsdStatusByPlaceDTO esdStatusByPlaceDTO : esdStatusByPlaceMachineDTOS){
            if(esdPlaceMachineMap.containsKey(esdStatusByPlaceDTO.getPlace())){
                esdStatusByPlaceDTO.setFailEsd(esdPlaceMachineMap.get(esdStatusByPlaceDTO.getPlace()));
            }
        }

        //看板配置
        List<ThEsdConfigEntity> esdConfigEntities = esdConfigService.list();

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "temandhum为温度数据," +
                "esdstatusMan为人员ESD数据," +
                "esdstatusMachine为机器ESD数据," +
                "boardconfig为看板配置数据" +
                "}");
        data.put("temandhum", realTimeTemAndHumEntityList);
        data.put("esdstatusMan", esdStatusByPlaceManDTOS);
        data.put("esdstatusMachine", esdStatusByPlaceMachineDTOS);
        data.put("boardconfig", esdConfigEntities.get(0));

        sendEmail("temperatureesd",esdConfigEntities.get(0).getEmail().split(";"),esdConfigEntities.get(0));

        return R.ok()
                .put("data", data)
                ;
    }


    @Autowired
    private ThOrderConfigService orderConfigService;

    private List<OrderCycleTimeOutDTO> orderCycleTimeOutDTOS;
    /**
     * 订单看板接口
     * @return
     */
    @GetMapping("/order")
    @ApiOperation(value = "订单看板接口",produces = "application/json")
    public R order(){

        //按照订单维度查询交付分析数据 查询当月
        DeliveryByOrderDTO deliveryByOrderDTO = orderDeliveryRecordService.queryDeliveryByOrder();

        //按照订单数量维度查询交付分析数据  查询当月
        List<DeliveryByOrderQtyDTO> deliveryByOrderQtyDTOS = orderProQuantityService.queryDeliveryByOrderQty();

        //格式转换
        LinkedHashMap<String,Object> deliveryByOrderQtydata = new LinkedHashMap();
        List<String> xAxisData = Lists.newArrayList();
        List<BigDecimal> dataPay = Lists.newArrayList();
        List<BigDecimal> dataNotPay = Lists.newArrayList();

        for(DeliveryByOrderQtyDTO deliveryByOrderQtyDTO : deliveryByOrderQtyDTOS){
            xAxisData.add(deliveryByOrderQtyDTO.getTime());
            dataPay.add(new BigDecimal(deliveryByOrderQtyDTO.getOrderDeliveryQty()));
            dataNotPay.add(new BigDecimal(deliveryByOrderQtyDTO.getOrderUndeliveryQty()));
        }

        deliveryByOrderQtydata.put("xAxisData",xAxisData);
        deliveryByOrderQtydata.put("dataPay",dataPay);
        deliveryByOrderQtydata.put("dataNotPay",dataNotPay);

        //查询未交付分析生产数量 查询当月
        List<UndeliveryPerByQtyDTO> undeliveryPerByQtyDTOS = undeliveredDetailsService.queryUndeliveryPerByQty();
        for (UndeliveryPerByQtyDTO undeliveryPerByQtyDTO : undeliveryPerByQtyDTOS){
            undeliveryPerByQtyDTO.setName(ZhConverterUtil.convertToSimple(undeliveryPerByQtyDTO.getName()));
        }

        //查询交付周期详细数据 查询当月
        orderCycleTimeOutDTOS = orderCycleTimeoutService.queryOrderCycleTimeOut();
        //过滤数据 只取10条
        List<OrderCycleTimeOutDTO> orderCycleTimeOutDTOList = Lists.newArrayList();
        if(orderCycleTimeOutDTOS.size()>=10){
            //获取前10条进行数据返回
            orderCycleTimeOutDTOList = orderCycleTimeOutDTOS.subList(0,10);
        }else{
            //小于10条则返回全部
            orderCycleTimeOutDTOList = orderCycleTimeOutDTOS;
        }

        //查询交付周期百分比数据 查询当月
        OrderCycleTimeOutPerctDTO orderCycleTimeOutPerctDTO = orderCycleTimeoutService.queryOrderCycleTimeOutPerct();

        OrderCycleTimeOutVO orderCycleTimeOutVO = new OrderCycleTimeOutVO();
        orderCycleTimeOutVO.setOrderCycleTimeOutDTOS(orderCycleTimeOutDTOList);
        orderCycleTimeOutVO.setOrderCycleTimeOutPerctDTO(orderCycleTimeOutPerctDTO);

        //看板配置
        List<ThOrderConfigEntity> orderConfigEntities = orderConfigService.list();

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "deliveryByOrder为未交付分析订单维度," +
                "deliveryByOrderQty为交付分析数量维度," +
                "undeliveryPerByQty为未交付分析数量," +
                "orderCycleTimeOut为订单交付周期追踪," +
                "boardconfig为看板配置数据" +
                "}");
        data.put("deliveryByOrder", deliveryByOrderDTO);
        data.put("deliveryByOrderQty", deliveryByOrderQtydata);
        data.put("undeliveryPerByQty", undeliveryPerByQtyDTOS);
        data.put("orderCycleTimeOut", orderCycleTimeOutVO);
        data.put("boardconfig", orderConfigEntities.get(0));

        sendEmail("order",orderConfigEntities.get(0).getEmail().split(";"),orderConfigEntities.get(0));

        return R.ok()
                .put("data", data)
                ;
    }

    @Autowired
    private ThMaintenanceConfigService maintenanceConfigService;

    @Autowired
    private ThVeneerWipService veneerWipService;

    @Autowired
    private ThTworefluxService tworefluxService;



    /**
     * 维修看板接口
     * @param timeType
     * @param maintainStep
     * @return
     */
    @GetMapping("/maintenance")
    @ApiOperation(value = "维修看板接口",produces = "application/json")
    public R maintenance(@ApiParam(name="timeType") @RequestParam(defaultValue = "dd")  String timeType,@ApiParam(name="maintainStep") @RequestParam(defaultValue = "1")  String maintainStep){

        //查询维修周期管理数据
        MaintenanceDetailPieDTO maintenanceDetailPieDTO = maintenanceDetailService.queryMaintenanceDetailPie(maintainStep,timeType);

        //查询维修超期TOP10
        List<MaintenanceCycleTop10DTO> maintenanceCycleTop10DTOS = maintenanceCycleService.queryMaintenanceCycleTop10(maintainStep,timeType);

        //数据量处理
        List<MaintenanceCycleTop10DTO> maintenanceCycleTop10DTOSData = Lists.newArrayList();
        if(maintenanceCycleTop10DTOS.size()>10){
            maintenanceCycleTop10DTOSData = maintenanceCycleTop10DTOS.subList(0,10);
        }else{
            maintenanceCycleTop10DTOSData = maintenanceCycleTop10DTOS;

        }

        //查询维修数量
        MaintenanceNumberDTO maintenanceNumberDTO = maintenanceNumberService.queryMaintenanceNumber(timeType);

        //趋势
        Set<String> xAxisDataSet = new HashSet<>();
        Map<String,Object> trendData = new HashMap<>();
        List<MaintenancetrendBrforeVO>  maintenancetrendBrfore4VOS= maintenanceDetailService.queryMaintenancetrendBrfore4();
        LinkedHashMap<String,Object> maintenancetrendBrfore4VOSMap = new LinkedHashMap();
        for (MaintenancetrendBrforeVO maintenancetrendBrforeVO:maintenancetrendBrfore4VOS){
            maintenancetrendBrfore4VOSMap.put(maintenancetrendBrforeVO.getName(),maintenancetrendBrforeVO.getNum());
            xAxisDataSet.add(ZhConverterUtil.convertToSimple(maintenancetrendBrforeVO.getName()));
        }
        List<MaintenancetrendBrforeVO>  maintenancetrendBrfore8VOS= maintenanceDetailService. queryMaintenancetrendBrfore8();
        LinkedHashMap<String,Object> maintenancetrendBrfore8VOSMap = new LinkedHashMap();
        for (MaintenancetrendBrforeVO maintenancetrendBrforeVO:maintenancetrendBrfore8VOS){
            maintenancetrendBrfore8VOSMap.put(maintenancetrendBrforeVO.getName(),maintenancetrendBrforeVO.getNum());
            xAxisDataSet.add(ZhConverterUtil.convertToSimple(maintenancetrendBrforeVO.getName()));
        }
        List<MaintenancetrendBrforeVO>  maintenancetrendBrfore12VOS= maintenanceDetailService. queryMaintenancetrendBrfore12();
        LinkedHashMap<String,Object> maintenancetrendBrfore12VOSMap = new LinkedHashMap();
        for (MaintenancetrendBrforeVO maintenancetrendBrforeVO:maintenancetrendBrfore12VOS){
            maintenancetrendBrfore12VOSMap.put(maintenancetrendBrforeVO.getName(),maintenancetrendBrforeVO.getNum());
            xAxisDataSet.add(ZhConverterUtil.convertToSimple(maintenancetrendBrforeVO.getName()));
        }
        List<MaintenancetrendBrforeVO>  maintenancetrendBrfore16VOS= maintenanceDetailService. queryMaintenancetrendBrfore16();
        LinkedHashMap<String,Object> maintenancetrendBrfore16VOSMap = new LinkedHashMap();
        for (MaintenancetrendBrforeVO maintenancetrendBrforeVO:maintenancetrendBrfore16VOS){
            maintenancetrendBrfore16VOSMap.put(maintenancetrendBrforeVO.getName(),maintenancetrendBrforeVO.getNum());
            xAxisDataSet.add(ZhConverterUtil.convertToSimple(maintenancetrendBrforeVO.getName()));
        }
        List<MaintenancetrendBrforeVO>  maintenancetrendBrfore20VOS= maintenanceDetailService. queryMaintenancetrendBrfore20();
        LinkedHashMap<String,Object> maintenancetrendBrfore20VOSMap = new LinkedHashMap();
        for (MaintenancetrendBrforeVO maintenancetrendBrforeVO:maintenancetrendBrfore20VOS){
            maintenancetrendBrfore20VOSMap.put(maintenancetrendBrforeVO.getName(),maintenancetrendBrforeVO.getNum());
            xAxisDataSet.add(ZhConverterUtil.convertToSimple(maintenancetrendBrforeVO.getName()));
        }
        List<MaintenancetrendBrforeVO>  maintenancetrendBrfore24VOS= maintenanceDetailService. queryMaintenancetrendBrfore24();
        LinkedHashMap<String,Object> maintenancetrendBrfore24VOSMap = new LinkedHashMap();
        for (MaintenancetrendBrforeVO maintenancetrendBrforeVO:maintenancetrendBrfore24VOS){
            maintenancetrendBrfore24VOSMap.put(maintenancetrendBrforeVO.getName(),maintenancetrendBrforeVO.getNum());
            xAxisDataSet.add(ZhConverterUtil.convertToSimple(maintenancetrendBrforeVO.getName()));
        }

        List<TrendSeriesVO> seriesList = Lists.newArrayList();
        //04:00
        TrendSeriesVO trendSeriesVO04 =new TrendSeriesVO();
        trendSeriesVO04.setName("04:00");
        List<BigDecimal> trendSeriesVO04Data = Lists.newArrayList();
        Iterator it = xAxisDataSet.iterator();
        while (it.hasNext()) {
            String str = (String)it.next();
            trendSeriesVO04Data.add(new BigDecimal((String)maintenancetrendBrfore4VOSMap.get(str)==null?"0":(String)maintenancetrendBrfore4VOSMap.get(str)));
        }
        trendSeriesVO04.setData(trendSeriesVO04Data);
        trendSeriesVO04.setData(trendSeriesVO04Data);
        //08:00
        TrendSeriesVO trendSeriesVO08 =new TrendSeriesVO();
        trendSeriesVO08.setName("08:00");
        List<BigDecimal> trendSeriesVO08Data = Lists.newArrayList();
        Iterator it2 = xAxisDataSet.iterator();
        while (it2.hasNext()) {
            String str = (String)it2.next();
            trendSeriesVO08Data.add(new BigDecimal((String)maintenancetrendBrfore8VOSMap.get(str)==null?"0":(String)maintenancetrendBrfore8VOSMap.get(str)));
        }
        trendSeriesVO08.setData(trendSeriesVO08Data);
        //12:00
        TrendSeriesVO trendSeriesVO12 =new TrendSeriesVO();
        trendSeriesVO12.setName("12:00");
        List<BigDecimal> trendSeriesVO12Data = Lists.newArrayList();
        Iterator it3 = xAxisDataSet.iterator();
        while (it3.hasNext()) {
            String str = (String)it3.next();
            trendSeriesVO12Data.add(new BigDecimal((String)maintenancetrendBrfore12VOSMap.get(str)==null?"0":(String)maintenancetrendBrfore12VOSMap.get(str)));
        }
        trendSeriesVO12.setData(trendSeriesVO12Data);
        //16:00
        TrendSeriesVO trendSeriesVO16 =new TrendSeriesVO();
        trendSeriesVO16.setName("16:00");
        List<BigDecimal> trendSeriesVO16Data = Lists.newArrayList();
        Iterator it4 = xAxisDataSet.iterator();
        while (it4.hasNext()) {
            String str = (String)it4.next();
            trendSeriesVO16Data.add(new BigDecimal((String)maintenancetrendBrfore16VOSMap.get(str)==null?"0":(String)maintenancetrendBrfore16VOSMap.get(str)));
        }
        trendSeriesVO16.setData(trendSeriesVO16Data);
        //20:00
        TrendSeriesVO trendSeriesVO20 =new TrendSeriesVO();
        trendSeriesVO20.setName("20:00");
        List<BigDecimal> trendSeriesVO20Data = Lists.newArrayList();
        Iterator it5 = xAxisDataSet.iterator();
        while (it5.hasNext()) {
            String str = (String)it5.next();
            trendSeriesVO20Data.add(new BigDecimal((String)maintenancetrendBrfore20VOSMap.get(str)==null?"0":(String)maintenancetrendBrfore20VOSMap.get(str)));
        }
        trendSeriesVO20.setData(trendSeriesVO20Data);
        //24:00
        TrendSeriesVO trendSeriesVO24 =new TrendSeriesVO();
        trendSeriesVO24.setName("24:00");
        List<BigDecimal> trendSeriesVO24Data = Lists.newArrayList();
        Iterator it6 = xAxisDataSet.iterator();
        while (it6.hasNext()) {
            String str = (String)it6.next();
            trendSeriesVO24Data.add(new BigDecimal((String)maintenancetrendBrfore24VOSMap.get(str)==null?"0":(String)maintenancetrendBrfore24VOSMap.get(str)));
        }
        trendSeriesVO24.setData(trendSeriesVO24Data);

        seriesList.add(trendSeriesVO04);
        seriesList.add(trendSeriesVO08);
        seriesList.add(trendSeriesVO12);
        seriesList.add(trendSeriesVO16);
        seriesList.add(trendSeriesVO20);
        seriesList.add(trendSeriesVO24);

        trendData.put("xAxisData",xAxisDataSet.toArray(new String[xAxisDataSet.size()]));
        trendData.put("series",seriesList);

        //看板配置
        List<ThMaintenanceConfigEntity> maintenanceConfigEntities= maintenanceConfigService.list();

        //单板WIP趋势
        List<VeneerWipVO> veneerWipVOS = veneerWipService.queryVeneerWip(timeType);
        //格式转换
        LinkedHashMap<String,Object> veneerWipdata = new LinkedHashMap();
        List<String> veneerWipxaxis = Lists.newArrayList();
        List<BigDecimal> veneerWipgoalData = Lists.newArrayList();
        List<BigDecimal> veneerWiptrueData = Lists.newArrayList();
        for(VeneerWipVO veneerWipVO : veneerWipVOS){
            veneerWipxaxis.add(veneerWipVO.getTime());
            veneerWipgoalData.add(new BigDecimal(maintenanceConfigEntities.get(0).getWipStandardData()));
            veneerWiptrueData.add(new BigDecimal(veneerWipVO.getNum()));
        }
        veneerWipdata.put("xaxis",veneerWipxaxis);
        veneerWipdata.put("goalData",veneerWipgoalData);
        veneerWipdata.put("trueData",veneerWiptrueData);

        //二次回流  查询之前月份的数组
        List<TwoRefluxVO> twoRefluxVOS = tworefluxService.queryTwoReflux();
        //格式转换
        LinkedHashMap<String,Object> twoRefluxdata = new LinkedHashMap();
        List<String> xaxis = Lists.newArrayList();
        List<BigDecimal> goalData = Lists.newArrayList();
        List<BigDecimal> trueData = Lists.newArrayList();
        for(TwoRefluxVO twoRefluxVO : twoRefluxVOS){
            xaxis.add(twoRefluxVO.getTime());
            goalData.add(new BigDecimal(maintenanceConfigEntities.get(0).getTwoRefluxStandardData()));
            trueData.add(new BigDecimal(twoRefluxVO.getPercent()));
        }

        twoRefluxdata.put("xaxis",xaxis);
        twoRefluxdata.put("goalData",goalData);
        twoRefluxdata.put("trueData",trueData);

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "maintenanceDetailPie为维修周期管理," +
                "maintenanceCycleTop10为维修超期TOP10," +
                "maintenanceNumber为维修数量" +
                "veneerWip为单板WIP趋势" +
                "tworeflux为二次回流" +
                "trendData为不良分布趋势图" +
                "boardconfig为看板配置数据" +
                "}");
        data.put("maintenanceDetailPie", maintenanceDetailPieDTO);
        data.put("maintenanceCycleTop10", maintenanceCycleTop10DTOSData);
        data.put("maintenanceNumber", maintenanceNumberDTO);
        data.put("veneerWip", veneerWipdata);
        data.put("tworeflux", twoRefluxdata);
        data.put("trendData", trendData);
        data.put("boardconfig", maintenanceConfigEntities.get(0));

        sendEmail("maintenance",maintenanceConfigEntities.get(0).getEmail().split(";"),maintenanceConfigEntities.get(0));

        return R.ok()
                .put("data", data)
                ;
    }


    @Autowired
    private ThRateConfigService rateConfigService;


    private List<DataDTO> proLineAchRateEntityList;
    /**
     * 产出率看板接口
     *
     * 各工序产出达成率
     * 各工序在制品
     *
     * 支持近2小时  和  一整天
     *
     * @timeType 传入参数名:timetype,参数值：近两小时(in2hour)或者一整天(today)
     * @return
     */
    @GetMapping("/producerate")
    @ApiOperation(value = "达成率(产出)看板接口",produces = "application/json")
    public R producerate(@ApiParam(name="timetype") @RequestParam(defaultValue = "in2hour")  String timeType){
        //达成率 查询周期数据

        String timeRange = proLineAchRateService.queryTimeRange(timeType);
        String timeStart = timeRange.split("--")[0];
        String timeEnd = timeRange.split("--")[1];
        //前端只要时间段不要  年月日
        DataRangeTimeVO dataRangeTimeVO = new DataRangeTimeVO(timeStart.split(" ")[1],timeEnd.split(" ")[1]);

        //查询各工序产出达成率
        proLineAchRateEntityList= proLineAchRateService.queryList(timeType,timeRange);
        for (int i = 0; i < proLineAchRateEntityList.size(); i++) {
            proLineAchRateEntityList.get(i).setLineName(ZhConverterUtil.convertToSimple(proLineAchRateEntityList.get(i).getLineName()));
        }

        List<DataCategoryDTO> dataCategoryDTOS= proLineAchRateService.queryListCatrgory(timeType,timeRange);
        //数据处理  将大类的数据进行封装
        Map<String,List<String>> dataCategoryMap = new HashMap<>();
        for(DataCategoryDTO dataCategoryDTO : dataCategoryDTOS){
            if(dataCategoryMap.containsKey(dataCategoryDTO.getLineName())){
                List<String> categorys = dataCategoryMap.get(dataCategoryDTO.getLineName());
                categorys.add(dataCategoryDTO.getCategory());
                dataCategoryMap.put(dataCategoryDTO.getLineName(),categorys);
            }else{
                List<String> categorys = Lists.newArrayList();
                categorys.add(ZhConverterUtil.convertToSimple(dataCategoryDTO.getCategory()));
                dataCategoryMap.put(dataCategoryDTO.getLineName(),categorys);
            }
        }
        //将返回的对象设置下fail的 category
        for(DataDTO dataDTO : proLineAchRateEntityList){
            if(dataCategoryMap.containsKey(dataDTO.getLineName())){
                dataDTO.setCatrgorys(dataCategoryMap.get(dataDTO.getLineName())==null?Lists.newArrayList():dataCategoryMap.get(dataDTO.getLineName()));
            }
        }

        //上周达标  这周达标   上周不达标   这周不达标
        DataDTO PreWeekTop1Ach = proLineAchRateService.queryPreWeekTop1Ach();
        DataDTO NowWeekTop1Ach = proLineAchRateService.queryNowWeekTop1Ach();

        DataDTO PreWeekTop1NoAch = proLineAchRateService.queryPreWeekTop1NoAch();
        DataDTO NowWeekTop1NoAch = proLineAchRateService.queryNowWeekTop1NoAch();

        //查询各工序在制品数量
        List<EachSiteWipDataDTO> eachSiteWipEntityList=eachSiteWipService.queryList(timeType,timeRange);
        for(EachSiteWipDataDTO eachSiteWipDataDTO : eachSiteWipEntityList){
            eachSiteWipDataDTO.setStationName(ZhConverterUtil.convertToSimple(eachSiteWipDataDTO.getStationName()));
        }

        //看板配置
        List<ThRateConfigEntity> rateConfigEntitys = rateConfigService.list();

        LinkedHashMap<String,Object> data = new LinkedHashMap();
        data.put("description", "{" +
                "datarangetime为查询时间周期," +
                "completionrate为各工序产出达成率," +
                "top1datas为上周达标本周达标，上周未达标本周未达标," +
                "wipnumber为各工序在制品," +
                "boardconfig为看板配置数据" +
                "}");
        data.put("datarangetime", dataRangeTimeVO);
        LinkedHashMap<String,Object> top1datas = new LinkedHashMap();
        top1datas.put("PreWeekTop1Ach",PreWeekTop1Ach);
        top1datas.put("NowWeekTop1Ach",NowWeekTop1Ach);
        top1datas.put("PreWeekTop1NoAch",PreWeekTop1NoAch);
        top1datas.put("NowWeekTop1NoAch",NowWeekTop1NoAch);
        data.put("top1datas", top1datas);
        data.put("completionrate", proLineAchRateEntityList);
        data.put("wipnumber", eachSiteWipEntityList);
        data.put("boardconfig", rateConfigEntitys.get(0));

        if(task!=null){
            task.cancel(true);
        }
        task = new SwingWorker<String, Object>() {
            protected String doInBackground() throws Exception {
                // 此处处于 SwingWorker 线程池中
                // 延时 5 秒，模拟耗时操作
                sendEmail("producerate",rateConfigEntitys.get(0).getEmail().split(";"),rateConfigEntitys.get(0));
                // 返回计算结果
                return "finish";
            }

            protected void done() {
                // 此方法将在后台任务完成后在事件调度线程中被回调
                String result = null;
                try {
                    // 获取计算结果
//                            result = get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        task.execute();

        return R.ok()
                .put("data", data)
                ;
    }

    boolean isEnableEmail = false; //激活邮件功能

    /**
     * 邮件发送
     * @param boardType
     */
    @Async
    public  void sendEmail(String boardType,String[] receiveEmails,Object obj){
        StringBuilder body = new StringBuilder();
        XSSFWorkbook wb = null;
        //判断看板类型
        if(isEnableEmail){
            String boardName = "";
            if(("quality".equals(boardType))){
                boardName = "质量看板";
                //不需要推送
            }else if(("euipment".equals(boardType))){
                boardName = "工序状态看板";

                ThEuipmentConfigEntity rateConfig = (ThEuipmentConfigEntity)obj;

//创建工作簿
                wb = new XSSFWorkbook();
                //创建一个sheet
                XSSFSheet sheet = wb.createSheet();
                // 创建单元格样式
                XSSFCellStyle style =  wb.createCellStyle();
                style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                style.setBorderTop(BorderStyle.THIN); //上边框加黑


                sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
                sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));
                //sheet.addMergedRegion(new CellRangeAddress(2,3,0,5));

                Row row1 = sheet.createRow(0);
                Cell cell1 = row1.createCell(0);
                cell1.setCellValue("各工序推送");

                Row row2 = sheet.createRow(1);
                Cell cell2 = row2.createCell(0);
                cell2.setCellValue("所有设备停机超1H");

                Row row3 = sheet.createRow(2);
                Cell cell3 = row3.createCell(0);
                //cell3.setCellValue("印刷/炉后AOI品质批量异常");

                //查询停机超过1小时数据
                List<EquipmentGL1hDTO> equipmentGL1hDTOS = euipmentStatusService.queryEquipmentGL1h();
                //获取需要的数据  //写入Excel文件
                for (int i = 0; i < equipmentGL1hDTOS.size(); i++) {
                    EquipmentGL1hDTO equipmentGL1hDTO = equipmentGL1hDTOS.get(i);
                    Row row =	sheet.createRow(i+5); //创建行
                    for (int j = 0; j < 3; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(j==0){
                                cell.setCellValue("设备型号");
                            }else if(j==1){
                                cell.setCellValue("设备名称");
                            }else if(j==2){
                                cell.setCellValue("已停机时间");
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(equipmentGL1hDTO.getEuipmentId());
                            }else if(j==1){
                                cell.setCellValue(equipmentGL1hDTO.getEuipmentName());
                            }else if(j==2){
                                cell.setCellValue(equipmentGL1hDTO.getOvertime()+" h");
                            }
                        }
                    }
                }

                sheet.autoSizeColumn((short)0); //调整第一列宽度
                sheet.autoSizeColumn((short)1); //调整第二列宽度
                sheet.autoSizeColumn((short)2); //调整第三列宽度

            }else if(("auto".equals(boardType))){
                boardName = "自动化设备看板";
                ThAutoConfigEntity autoConfigEntity = (ThAutoConfigEntity)obj;

                //创建工作簿
                wb = new XSSFWorkbook();
                //创建一个sheet
                XSSFSheet sheet = wb.createSheet();
                // 创建单元格样式
                XSSFCellStyle style =  wb.createCellStyle();
                style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                style.setBorderTop(BorderStyle.THIN); //上边框加黑
                //查询保养  TODO  没有保养信息 无法做

                //查询1H内5次报警数据
                List<AutoStatusWarnDTO> autoStatusWarnDTOS = autoStatusService.queryWarn5BylistNew();
                //获取需要的数据  //写入Excel文件
                for (int i = 0; i < autoStatusWarnDTOS.size(); i++) {
                    AutoStatusWarnDTO autoStatusWarnDTO = autoStatusWarnDTOS.get(i);
                    Row row =	sheet.createRow(i); //创建行
                    for (int j = 0; j < 5; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(j==0){
                                cell.setCellValue("序号");
                            }else if(j==1){
                                cell.setCellValue("推送项目");
                            }else if(j==2){
                                cell.setCellValue("推送内容");
                            }else if(j==3){
                                cell.setCellValue("刷新频率");
                            }else if(j==4){
                                cell.setCellValue("解除方式");
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(i+1);
                            }else if(j==1){
                                cell.setCellValue("1H内大于5次设备报警数据");
                            }else if(j==2){
                                cell.setCellValue(autoStatusWarnDTO.getEuipmentName()+";"+autoStatusWarnDTO.getTimes()+" 次");
                            }else if(j==3){
                                cell.setCellValue(new BigDecimal(autoConfigEntity.getRefreshTime()).divide(new BigDecimal(3600),2,BigDecimal.ROUND_HALF_UP).toString()+" 小时");
                            }else if(j==4){
                                cell.setCellValue("下一时间段小于5次");
                            }
                        }
                    }
                }



                //查询运行状态
                List<AutoStatusDTO> autoStatusDTOS = autoStatusService.queryStatusBylistNew();
                for (int i = 0; i < autoStatusDTOS.size(); i++) {
                    AutoStatusDTO autoStatusDTO = autoStatusDTOS.get(i);
                    Row row =	sheet.createRow(i); //创建行
                    for (int j = 0; j < 5; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(autoStatusWarnDTOS.size()==0){
                                if(j==0){
                                    cell.setCellValue("序号");
                                }else if(j==1){
                                    cell.setCellValue("推送项目");
                                }else if(j==2){
                                    cell.setCellValue("推送内容");
                                }else if(j==3){
                                    cell.setCellValue("刷新频率");
                                }else if(j==4){
                                    cell.setCellValue("解除方式");
                                }
                            }else{
                                if(j==0){
                                    cell.setCellValue(autoStatusWarnDTOS.size()+i);
                                }else if(j==1){
                                    cell.setCellValue("设备运行状况");
                                }else if(j==2){
                                    cell.setCellValue(autoStatusDTO.getEuipmentName()+";"+ autoStatusDTO.getNum()+" 个;"+autoStatusDTO.getTimeRange());
                                }else if(j==3){
                                    cell.setCellValue(new BigDecimal(autoConfigEntity.getRefreshTime()).divide(new BigDecimal(3600),2,BigDecimal.ROUND_HALF_UP).toString()+" 小时");
                                }else if(j==4){
                                    cell.setCellValue("反复更新");
                                }
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(autoStatusWarnDTOS.size()+i);
                            }else if(j==1){
                                cell.setCellValue("设备运行状况");
                            }else if(j==2){
                                cell.setCellValue(autoStatusDTO.getEuipmentName()+";"+ autoStatusDTO.getNum()+" 个;"+autoStatusDTO.getTimeRange());
                            }else if(j==3){
                                cell.setCellValue(new BigDecimal(autoConfigEntity.getRefreshTime()).divide(new BigDecimal(3600),2,BigDecimal.ROUND_HALF_UP).toString()+" 小时");
                            }else if(j==4){
                                cell.setCellValue("反复更新");
                            }
                        }
                    }
                }

                sheet.autoSizeColumn((short)0); //调整第一列宽度
                sheet.autoSizeColumn((short)1); //调整第二列宽度
                sheet.autoSizeColumn((short)2); //调整第三列宽度
                sheet.autoSizeColumn((short)3); //调整第四列宽度
                sheet.autoSizeColumn((short)4); //调整第四列宽度

            }else if(("temperatureesd".equals(boardType))){
                boardName = "温湿度ESD实时看板";

                ThEsdConfigEntity esdConfig = (ThEsdConfigEntity)obj;

                //创建工作簿
                wb = new XSSFWorkbook();
                //创建一个sheet
                XSSFSheet sheet = wb.createSheet();
                // 创建单元格样式
                XSSFCellStyle style =  wb.createCellStyle();
                style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                style.setBorderTop(BorderStyle.THIN); //上边框加黑

                sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));

                Row row1 = sheet.createRow(0);
                Cell cell1 = row1.createCell(0);
                cell1.setCellValue("温湿度 ESD推送要求（温湿度 ESD不达标时推送）");


                sheet.addMergedRegion(new CellRangeAddress(2,2,0,5));
                Row row2 = sheet.createRow(2);
                Cell cell2 = row2.createCell(0);
                cell2.setCellValue("ESD人体接地监控");

                //查询人员ESD
                List<ESDWarnDTO> manESDWarn = esdRealTimeService.queryManESDWarn();
                //获取需要的数据  //写入Excel文件
                for (int i = 0; i < manESDWarn.size(); i++) {
                    ESDWarnDTO esdWarnDTO = manESDWarn.get(i);
                    Row row =	sheet.createRow(i+3); //创建行
                    for (int j = 0; j < 3; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(j==0){
                                cell.setCellValue("日期-时间");
                            }else if(j==1){
                                cell.setCellValue("区域");
                            }else if(j==2){
                                cell.setCellValue("人体设备编号");
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(esdWarnDTO.getTime());
                            }else if(j==1){
                                cell.setCellValue(ZhConverterUtil.convertToSimple(esdWarnDTO.getPlace()));
                            }else if(j==2){
                                cell.setCellValue(esdWarnDTO.getId());
                            }
                        }
                    }
                }

                sheet.addMergedRegion(new CellRangeAddress(manESDWarn.size()+4,manESDWarn.size()+4,0,5));
                Row row3 = sheet.createRow(manESDWarn.size()+4);
                Cell cell3 = row3.createCell(0);
                cell3.setCellValue("ESD设备接地监控");

                //查询设备ESD
                List<ESDWarnDTO> machineESDWarn = esdRealTimeService.queryMachineESDWarn();
                //获取需要的数据  //写入Excel文件
                for (int i = 0; i < machineESDWarn.size(); i++) {
                    ESDWarnDTO esdWarnDTO = machineESDWarn.get(i);
                    Row row =	sheet.createRow(i+4+manESDWarn.size()+1); //创建行
                    for (int j = 0; j < 3; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(j==0){
                                cell.setCellValue("日期-时间");
                            }else if(j==1){
                                cell.setCellValue("区域");
                            }else if(j==2){
                                cell.setCellValue("人体设备编号");
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(esdWarnDTO.getTime());
                            }else if(j==1){
                                cell.setCellValue(ZhConverterUtil.convertToSimple(esdWarnDTO.getPlace()));
                            }else if(j==2){
                                cell.setCellValue(esdWarnDTO.getId());
                            }
                        }
                    }
                }

                sheet.addMergedRegion(new CellRangeAddress(manESDWarn.size()+machineESDWarn.size()+5,manESDWarn.size()+machineESDWarn.size()+5,0,5));
                Row row4 = sheet.createRow(manESDWarn.size()+machineESDWarn.size()+5);
                Cell cell4 = row4.createCell(0);
                cell4.setCellValue("温湿度监控");
                //查询温度湿度
                List<HumWarnDTO> humWarns = realTimeTemAndHumService.queryTempHumWarn();
                //获取需要的数据  //写入Excel文件
                for (int i = 0; i < humWarns.size(); i++) {
                    HumWarnDTO esdWarnDTO = humWarns.get(i);
                    Row row =	sheet.createRow(i+5+manESDWarn.size()+machineESDWarn.size()+1); //创建行
                    for (int j = 0; j < 3; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(j==0){
                                cell.setCellValue("日期-时间");
                            }else if(j==1){
                                cell.setCellValue("区域");
                            }else if(j==2){
                                cell.setCellValue("温度");
                            }else if(j==3){
                                cell.setCellValue("湿度");
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(esdWarnDTO.getTime());
                            }else if(j==1){
                                cell.setCellValue(ZhConverterUtil.convertToSimple(esdWarnDTO.getPlace()));
                            }else if(j==2){
                                cell.setCellValue(esdWarnDTO.getTem());
                            }else if(j==3){
                                cell.setCellValue(esdWarnDTO.getHum());
                            }
                        }
                    }
                }

                sheet.autoSizeColumn((short)0); //调整第一列宽度
                sheet.autoSizeColumn((short)1); //调整第二列宽度
                sheet.autoSizeColumn((short)2); //调整第三列宽度
                sheet.autoSizeColumn((short)3); //调整第三列宽度

            }else if(("order".equals(boardType))){
                boardName = "订单管理看板";

                ThOrderConfigEntity orderConfig = (ThOrderConfigEntity)obj;

                //创建工作簿
                wb = new XSSFWorkbook();
                //创建一个sheet
                XSSFSheet sheet = wb.createSheet();
                // 创建单元格样式
                XSSFCellStyle style =  wb.createCellStyle();
                style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                style.setBorderTop(BorderStyle.THIN); //上边框加黑

                //获取需要的数据  //写入Excel文件
                for (int i = 0; i < orderCycleTimeOutDTOS.size(); i++) {
                    if(i==10){break;}//10条就行了
                    OrderCycleTimeOutDTO orderCycleTimeOutDTO = orderCycleTimeOutDTOS.get(i);
                    Row row =	sheet.createRow(i); //创建行
                    for (int j = 0; j < 5; j++) {//需要6列
                        Cell cell = row.createCell(j);
                        if(i==0){//第一行
                            cell.setCellStyle(style);
                            if(j==0){
                                cell.setCellValue("序号");
                            }else if(j==1){
                                cell.setCellValue("推送项目");
                            }else if(j==2){
                                cell.setCellValue("推送内容");
                            }else if(j==3){
                                cell.setCellValue("刷新频率");
                            }else if(j==4){
                                cell.setCellValue("解除方式");
                            }
                        }else{
                            if(j==0){
                                cell.setCellValue(i+1);
                            }else if(j==1){
                                cell.setCellValue("超时TOP（10）");
                            }else if(j==2){
                                cell.setCellValue(orderCycleTimeOutDTO.getOrderNumber()+","+orderCycleTimeOutDTO.getOverTime()+" h");
                            }else if(j==3){
                                cell.setCellValue(new BigDecimal(orderConfig.getRefreshTime()).divide(new BigDecimal(3600),2,BigDecimal.ROUND_HALF_UP).toString()+" 小时");
                            }else if(j==4){
                                cell.setCellValue("/");
                            }
                        }
                    }
                }

                sheet.autoSizeColumn((short)0); //调整第一列宽度
                sheet.autoSizeColumn((short)1); //调整第二列宽度
                sheet.autoSizeColumn((short)2); //调整第三列宽度
                sheet.autoSizeColumn((short)3); //调整第四列宽度
                sheet.autoSizeColumn((short)4); //调整第四列宽度

            }else if(("maintenance".equals(boardType))){
                boardName = "维修管理看板";

                ThMaintenanceConfigEntity maintenanceConfig = (ThMaintenanceConfigEntity)obj;

                //创建工作簿
                wb = new XSSFWorkbook();
                //创建一个sheet
                XSSFSheet sheet = wb.createSheet();
                // 创建单元格样式
                XSSFCellStyle style =  wb.createCellStyle();
                style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                style.setBorderTop(BorderStyle.THIN); //上边框加黑


                sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));
                sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
                sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
                sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
                sheet.addMergedRegion(new CellRangeAddress(0,1,3,3));
                sheet.addMergedRegion(new CellRangeAddress(0,1,4,4));
                sheet.addMergedRegion(new CellRangeAddress(0,1,5,5));
                sheet.addMergedRegion(new CellRangeAddress(0,1,8,8));
                sheet.addMergedRegion(new CellRangeAddress(0,1,9,9));

                Row row1 = sheet.createRow(0);
                Cell cell1 = row1.createCell(0);
                cell1.setCellValue("名称");
                Cell cell2 = row1.createCell(1);
                cell2.setCellValue("负责单位");
                Cell cell3 = row1.createCell(2);
                cell3.setCellValue("工站");
                Cell cell4 = row1.createCell(3);
                cell4.setCellValue("不良\n" +
                        "数量");
                Cell cell5 = row1.createCell(4);
                cell5.setCellValue("＞5H");
                Cell cell6 = row1.createCell(5);
                cell6.setCellValue("<5H");
                Cell cell7 = row1.createCell(6);
                cell7.setCellValue("WK34");
                Cell cell8 = row1.createCell(8);
                cell8.setCellValue("未达成原因");
                Cell cell9 = row1.createCell(9);
                cell9.setCellValue("改善对策");

                Row row2 = sheet.createRow(1);
                Cell cell2_1 = row2.createCell(6);
                cell2_1.setCellValue("达成率");
                Cell cell2_2 = row2.createCell(7);
                cell2_2.setCellValue("平均维修时间(H)");


                //总维修周期
                Row row3 = sheet.createRow(2);
                Cell cell3_1 = row3.createCell(0);
                cell3_1.setCellValue("总维修周期");
                Cell cell3_2 = row3.createCell(1);
                cell3_2.setCellValue("RE");
                Cell cell3_3 = row3.createCell(2);
                cell3_3.setCellValue("失败时间-ALLLINK");
                Cell cell3_4 = row3.createCell(3);
                cell3_4.setCellValue("848");
                Cell cell3_5 = row3.createCell(4);
                cell3_5.setCellValue("302");
                Cell cell3_6 = row3.createCell(5);
                cell3_6.setCellValue("546");
                Cell cell3_7 = row3.createCell(6);
                cell3_7.setCellValue("88%");
                Cell cell3_8 = row3.createCell(7);
                cell3_8.setCellValue("5.39");
                Cell cell3_9 = row3.createCell(8);
                cell3_9.setCellValue("");
                Cell cell3_10 = row3.createCell(9);
                cell3_10.setCellValue("");


                sheet.addMergedRegion(new CellRangeAddress(3,5,0,0));


                //整机
                Row row4 = sheet.createRow(3);
                Cell cell4_1 = row4.createCell(0);
                cell4_1.setCellValue("整机");
                Cell cell4_2 = row4.createCell(1);
                cell4_2.setCellValue("负责单位");
                Cell cell4_3 = row4.createCell(2);
                cell4_3.setCellValue("工站");
                Cell cell4_4 = row4.createCell(3);
                cell4_4.setCellValue("数量");
                Cell cell4_5 = row4.createCell(4);
                cell4_5.setCellValue("＞2H");
                Cell cell4_6 = row4.createCell(5);
                cell4_6.setCellValue("＜2H");
                Cell cell4_7 = row4.createCell(6);
                cell4_7.setCellValue("达成率");
                Cell cell4_8 = row4.createCell(7);
                cell4_8.setCellValue("平均维修时间(H)");
                Cell cell4_9 = row4.createCell(8);
                cell4_9.setCellValue("");
                Cell cell4_10 = row4.createCell(9);
                cell4_10.setCellValue("");

                Row row5 = sheet.createRow(4);
                Cell cell5_2 = row5.createCell(1);
                cell5_2.setCellValue("TE");
                Cell cell5_3 = row5.createCell(2);
                cell5_3.setCellValue("失败时间-checkin时间");
                Cell cell5_4 = row5.createCell(3);
                cell5_4.setCellValue("306");
                Cell cell5_5 = row5.createCell(4);
                cell5_5.setCellValue("90");
                Cell cell5_6 = row5.createCell(5);
                cell5_6.setCellValue("216");
                Cell cell5_7 = row5.createCell(6);
                cell5_7.setCellValue("71%");
                Cell cell5_8 = row5.createCell(7);
                cell5_8.setCellValue("3.5");
                Cell cell5_9 = row5.createCell(8);
                cell5_9.setCellValue("");
                Cell cell5_10 = row5.createCell(9);
                cell5_10.setCellValue("");

                Row row6 = sheet.createRow(5);
                Cell cell6_2 = row6.createCell(1);
                cell6_2.setCellValue("RE");
                Cell cell6_3 = row6.createCell(2);
                cell6_3.setCellValue("checkin时间-ALLLINK");
                Cell cell6_4 = row6.createCell(3);
                cell6_4.setCellValue("306");
                Cell cell6_5 = row6.createCell(4);
                cell6_5.setCellValue("39");
                Cell cell6_6 = row6.createCell(5);
                cell6_6.setCellValue("267");
                Cell cell6_7 = row6.createCell(6);
                cell6_7.setCellValue("87%");
                Cell cell6_8 = row6.createCell(7);
                cell6_8.setCellValue("1.9");
                Cell cell6_9 = row6.createCell(8);
                cell6_9.setCellValue("");
                Cell cell6_10 = row6.createCell(9);
                cell6_10.setCellValue("");


                //NTF
                sheet.addMergedRegion(new CellRangeAddress(6,7,0,0));
                Row row7 = sheet.createRow(6);
                Cell cell7_1 = row7.createCell(0);
                cell7_1.setCellValue("NTF");
                Cell cell7_2 = row7.createCell(1);
                cell7_2.setCellValue("负责单位");
                Cell cell7_3 = row7.createCell(2);
                cell7_3.setCellValue("工站");
                Cell cell7_4 = row7.createCell(3);
                cell7_4.setCellValue("数量");
                Cell cell7_5 = row7.createCell(4);
                cell7_5.setCellValue("＞5H");
                Cell cell7_6 = row7.createCell(5);
                cell7_6.setCellValue("＜5H");
                Cell cell7_7 = row7.createCell(6);
                cell7_7.setCellValue("达成率");
                Cell cell7_8 = row7.createCell(7);
                cell7_8.setCellValue("平均维修时间(H)");
                Cell cell7_9 = row7.createCell(8);
                cell7_9.setCellValue("");
                Cell cell7_10 = row7.createCell(9);
                cell7_10.setCellValue("");

                Row row8 = sheet.createRow(7);
                Cell cell8_2 = row8.createCell(1);
                cell8_2.setCellValue("TE");
                Cell cell8_3 = row8.createCell(2);
                cell8_3.setCellValue("失败时间-terepair时间");
                Cell cell8_4 = row8.createCell(3);
                cell8_4.setCellValue("542");
                Cell cell8_5 = row8.createCell(4);
                cell8_5.setCellValue("172");
                Cell cell8_6 = row8.createCell(5);
                cell8_6.setCellValue("369");
                Cell cell8_7 = row8.createCell(6);
                cell8_7.setCellValue("68%");
                Cell cell8_8 = row8.createCell(7);
                cell8_8.setCellValue("3.4");
                Cell cell8_9 = row8.createCell(8);
                cell8_9.setCellValue("");
                Cell cell8_10 = row8.createCell(9);
                cell8_10.setCellValue("");


                //表格
                Row row9 = sheet.createRow(7+2);
                Cell cell9_1 = row9.createCell(0);
                cell9_1.setCellValue("NO");
                Cell cell9_2 = row9.createCell(1);
                cell9_2.setCellValue("sysserialno");
                Cell cell9_3 = row9.createCell(2);
                cell9_3.setCellValue("workorderno");
                Cell cell9_4 = row9.createCell(3);
                cell9_4.setCellValue("createdate");
                Cell cell9_5 = row9.createCell(4);
                cell9_5.setCellValue("failurestation");
                Cell cell9_6 = row9.createCell(5);
                cell9_6.setCellValue("checkintime");
                Cell cell9_7 = row9.createCell(6);
                cell9_7.setCellValue("repairstartdate");
                Cell cell9_8 = row9.createCell(7);
                cell9_8.setCellValue("checkouttime");
                Cell cell9_9 = row9.createCell(8);
                cell9_9.setCellValue("repairby");
                Cell cell9_10 = row9.createCell(9);
                cell9_10.setCellValue("scandatetime");
                Cell cell9_11 = row9.createCell(10);
                cell9_11.setCellValue("TO_NO");

            }else if(("producerate".equals(boardType))){
                boardName = "达成率产出管理看板";

                //ThRateConfigEntity rateConfigEntity = (ThRateConfigEntity)obj;

                if(proLineAchRateEntityList.size()>0){
                    //创建工作簿
                    wb = new XSSFWorkbook();
                    //创建一个sheet
                    XSSFSheet sheet = wb.createSheet();
                    // 创建单元格样式
                    XSSFCellStyle style =  wb.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                    style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                    style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                    style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                    style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                    style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                    style.setBorderTop(BorderStyle.THIN); //上边框加黑

                    //创建4行表头  1-4 表头  5 空行

                    sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
                    sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));
                    sheet.addMergedRegion(new CellRangeAddress(2,3,0,5));

                    Row row1 = sheet.createRow(0);
                    Cell cell1 = row1.createCell(0);
                    cell1.setCellValue("邮件推送内容");

                    Row row2 = sheet.createRow(1);
                    Cell cell2 = row2.createCell(0);
                    cell2.setCellValue("产能达成率");

                    Row row3 = sheet.createRow(2);
                    Cell cell3 = row3.createCell(0);
                    cell3.setCellValue("产能未达到90%推送，不推送时间段（交接班时间）\\r\\n" +
                            "：白班：11：00-13：00，17：00-19：00，\\r\\n" +
                            "夜班：22：30-0：30，5：00-7：00");


                    //获取需要的数据  //写入Excel文件
                    for (int i = 0; i < proLineAchRateEntityList.size(); i++) {
                        DataDTO dataDTO = proLineAchRateEntityList.get(i);
                        Row row =	sheet.createRow(i+5); //创建行
                        for (int j = 0; j < 6; j++) {//需要6列
                            Cell cell = row.createCell(j);
                            if(i==0){//第一行
                                cell.setCellStyle(style);
                                if(j==0){
                                    cell.setCellValue("线别");
                                }else if(j==1){
                                    cell.setCellValue("目标");
                                }else if(j==2){
                                    cell.setCellValue("实际");
                                }else if(j==3){
                                    cell.setCellValue("未达成原因");
                                }else if(j==4){
                                    cell.setCellValue("对策");
                                }else if(j==5){
                                    cell.setCellValue("责任人");
                                }
                            }else{
                                if(j==0){
                                    cell.setCellValue(dataDTO.getLineName());
                                }else if(j==1){
                                    cell.setCellValue(dataDTO.getAchRate());
                                }else if(j==2){
                                    cell.setCellValue(dataDTO.getStandardAchRate());
                                }else if(j==3){
                                    cell.setCellValue("无");
                                }else if(j==4){
                                    cell.setCellValue("无");
                                }else if(j==5){
                                    cell.setCellValue("无");
                                }
                            }
                        }
                    }

                    sheet.autoSizeColumn((short)0); //调整第一列宽度
                    sheet.autoSizeColumn((short)1); //调整第二列宽度
                    sheet.autoSizeColumn((short)2); //调整第三列宽度
                    sheet.autoSizeColumn((short)3); //调整第四列宽度
                    sheet.autoSizeColumn((short)4); //调整第四列宽度
                    sheet.autoSizeColumn((short)5);

                }

            }else if(("mateeral".equals(boardType))){
                boardName = "物料管理看板";


                ThMateeralConfigEntity mateeralConfig = (ThMateeralConfigEntity)obj;

                if(dayEachMachineThrowingRateVOS.size()>0){
                    //创建工作簿
                    wb = new XSSFWorkbook();
                    //创建一个sheet
                    XSSFSheet sheet = wb.createSheet();
                    // 创建单元格样式
                    XSSFCellStyle style =  wb.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.LIME.getIndex()); //设置要添加表格北京颜色
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
                    style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
                    style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
                    style.setBorderBottom(BorderStyle.THIN); //底边框加黑
                    style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
                    style.setBorderRight(BorderStyle.THIN); // 有边框加黑
                    style.setBorderTop(BorderStyle.THIN); //上边框加黑

                    //创建4行表头  1-4 表头  5 空行

                    sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
                    sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));

                    Row row1 = sheet.createRow(0);
                    Cell cell1 = row1.createCell(0);
                    cell1.setCellValue("抛料率达成");

                    Row row2 = sheet.createRow(1);
                    Cell cell2 = row2.createCell(0);
                    cell2.setCellValue("按机台推送：抛料率大于千分之三的TOP3");


                    int rowIndex = 5;
                    //获取需要的数据  //写入Excel文件
                    for (int i = 0; i < dayEachMachineThrowingRateVOS.size(); i++) {
                        DayEachMachineThrowingRateVO dataDTO = dayEachMachineThrowingRateVOS.get(i);
                        for (int j = 0; j < dataDTO.getTop3().size(); j++) {
                            MachineThrowingRateDTO machineThrowingRateDTO = dataDTO.getTop3().get(j);
                            Row row =	sheet.createRow(rowIndex); //创建行
                            for (int k = 0; k < 6; k++) {//需要6列
                                Cell cell = row.createCell(k);
                                if(rowIndex==5){//第一行
                                    cell.setCellStyle(style);
                                    if(k==0){
                                        cell.setCellValue("序列号");
                                    }else if(k==1){
                                        cell.setCellValue("机台号");
                                    }else if(k==2){
                                        cell.setCellValue("轨道号");
                                    }else if(k==3){
                                        cell.setCellValue("料号");
                                    }else if(k==4){
                                        cell.setCellValue("警戒值");
                                    }else if(k==5){
                                        cell.setCellValue("实际");
                                    }
                                }else{

                                    if(k==0){
                                        cell.setCellValue("TOP"+k+1);
                                    }else if(k==1){
                                        cell.setCellValue(machineThrowingRateDTO.getMachineNo());
                                    }else if(k==2){
                                        cell.setCellValue(machineThrowingRateDTO.getTrackNo());
                                    }else if(k==3){
                                        cell.setCellValue(machineThrowingRateDTO.getPartNumber());
                                    }else if(k==4){
                                        cell.setCellValue(mateeralConfig.getRateUp());
                                    }else if(k==5){
                                        cell.setCellValue(machineThrowingRateDTO.getThrowRate());
                                    }
                                }
                            }
                            rowIndex ++ ;
                        }
                    }

                    sheet.autoSizeColumn((short)0); //调整第一列宽度
                    sheet.autoSizeColumn((short)1); //调整第二列宽度
                    sheet.autoSizeColumn((short)2); //调整第三列宽度
                    sheet.autoSizeColumn((short)3); //调整第四列宽度
                    sheet.autoSizeColumn((short)4); //调整第四列宽度
                    sheet.autoSizeColumn((short)5);
                }



            }

            //查询看板需要报警的数据
            //组装格式和内容
            MimeMessage message=mailSender.createMimeMessage();

            try {
                //true表示需要创建一个multipart message
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                helper.setFrom("james_chen@amaxgs.com");//发送方邮件设置
                helper.setTo(receiveEmails);//接收方邮件设置 接收接收邮件数组
                String Name = "统合看板系统消息通知-"+boardName;
                helper.setSubject(Name);
                helper.setText("附件Excel是此次报警数据",true);

                InputStream in = null;
                //添加附件
                if(wb!=null){
                    try {
                        //临时缓冲区
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        //创建临时文件
                        wb.write(out);
                        byte[] bookByteAry = out.toByteArray();
                        in = new ByteArrayInputStream(bookByteAry);
                        helper.addAttachment(Name+".xlsx", new ByteArrayResource(IOUtils.toByteArray(in)), "application/vnd.ms-excel;charset=UTF-8");
                    } catch (Exception e) {
                    }
                }
                //发送邮件
                mailSender.send(message);
                System.out.println("html格式邮件发送成功");
            }catch (Exception e){
                System.out.println("html格式邮件发送失败");
            }
        }
    }
}
