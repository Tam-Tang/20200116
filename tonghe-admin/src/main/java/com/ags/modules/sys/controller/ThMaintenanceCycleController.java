package com.ags.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.ags.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ags.modules.sys.entity.ThMaintenanceCycleEntity;
import com.ags.modules.sys.service.ThMaintenanceCycleService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 维修周期超时记录
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:12:57
 */
@RestController
@RequestMapping("sys/thmaintenancecycle")
public class ThMaintenanceCycleController {
    @Autowired
    private ThMaintenanceCycleService thMaintenanceCycleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thmaintenancecycle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thMaintenanceCycleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thmaintenancecycle:info")
    public R info(@PathVariable("id") Long id){
        ThMaintenanceCycleEntity thMaintenanceCycle = thMaintenanceCycleService.getById(id);

        return R.ok().put("thMaintenanceCycle", thMaintenanceCycle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thmaintenancecycle:save")
    public R save(@RequestBody ThMaintenanceCycleEntity thMaintenanceCycle){
        thMaintenanceCycleService.save(thMaintenanceCycle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thmaintenancecycle:update")
    public R update(@RequestBody ThMaintenanceCycleEntity thMaintenanceCycle){
        ValidatorUtils.validateEntity(thMaintenanceCycle);
        thMaintenanceCycleService.updateById(thMaintenanceCycle);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thmaintenancecycle:delete")
    public R delete(@RequestBody Long[] ids){
        thMaintenanceCycleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
