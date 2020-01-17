package com.ags.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.ags.common.validator.ValidatorUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ags.modules.sys.entity.ThWipRedDataEntity;
import com.ags.modules.sys.service.ThWipRedDataService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * ${comments}
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-02 15:41:23
 */
@RestController
@RequestMapping("sys/thwipreddata")
public class ThWipRedDataController {
    @Autowired
    private ThWipRedDataService thWipRedDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thwipreddata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thWipRedDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thwipreddata:info")
    public R info(@PathVariable("id") Long id){
        ThWipRedDataEntity thWipRedData = thWipRedDataService.getById(id);

        return R.ok().put("thWipRedData", thWipRedData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thwipreddata:save")
    public R save(@RequestBody ThWipRedDataEntity thWipRedData){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("STATION_ID",thWipRedData.getStationId());
        if(thWipRedDataService.list(queryWrapper).size()==1){
            return R.error("相同站点只能维护一条信息!请在原先的数据上面修改");
        }

        thWipRedDataService.save(thWipRedData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thwipreddata:update")
    public R update(@RequestBody ThWipRedDataEntity thWipRedData){
        ValidatorUtils.validateEntity(thWipRedData);
        thWipRedDataService.updateById(thWipRedData);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thwipreddata:delete")
    public R delete(@RequestBody Long[] ids){
        thWipRedDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
