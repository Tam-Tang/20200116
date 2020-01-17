package com.ags.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.ags.common.utils.Query;
import com.ags.common.validator.ValidatorUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ags.modules.sys.entity.ThQualityYieldEntity;
import com.ags.modules.sys.service.ThQualityYieldService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 各产线良率标准表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 09:04:35
 */
@RestController
@RequestMapping("sys/thqualityyield")
public class ThQualityYieldController {
    @Autowired
    private ThQualityYieldService thQualityYieldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thqualityyield:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thQualityYieldService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thqualityyield:info")
    public R info(@PathVariable("id") Long id){
        ThQualityYieldEntity thQualityYield = thQualityYieldService.getById(id);

        return R.ok().put("thQualityYield", thQualityYield);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thqualityyield:save")
    public R save(@RequestBody ThQualityYieldEntity thQualityYield){

        //只允许新增一条
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("LINE_ID",thQualityYield.getLineId());
        queryWrapper.eq("STATION_ID",thQualityYield.getStationId());

        if(thQualityYieldService.list(queryWrapper).size()==1){
            return R.error("该产线已经维护该站点良率信息！");
        }

        thQualityYieldService.save(thQualityYield);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thqualityyield:update")
    public R update(@RequestBody ThQualityYieldEntity thQualityYield){
        ValidatorUtils.validateEntity(thQualityYield);
        thQualityYieldService.updateById(thQualityYield);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thqualityyield:delete")
    public R delete(@RequestBody Long[] ids){
        thQualityYieldService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
