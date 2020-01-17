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

import com.ags.modules.sys.entity.ThMaintenanceNumberEntity;
import com.ags.modules.sys.service.ThMaintenanceNumberService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 维修数量表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:00
 */
@RestController
@RequestMapping("sys/thmaintenancenumber")
public class ThMaintenanceNumberController {
    @Autowired
    private ThMaintenanceNumberService thMaintenanceNumberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thmaintenancenumber:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thMaintenanceNumberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thmaintenancenumber:info")
    public R info(@PathVariable("id") Long id){
        ThMaintenanceNumberEntity thMaintenanceNumber = thMaintenanceNumberService.getById(id);

        return R.ok().put("thMaintenanceNumber", thMaintenanceNumber);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thmaintenancenumber:save")
    public R save(@RequestBody ThMaintenanceNumberEntity thMaintenanceNumber){
        thMaintenanceNumberService.save(thMaintenanceNumber);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thmaintenancenumber:update")
    public R update(@RequestBody ThMaintenanceNumberEntity thMaintenanceNumber){
        ValidatorUtils.validateEntity(thMaintenanceNumber);
        thMaintenanceNumberService.updateById(thMaintenanceNumber);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thmaintenancenumber:delete")
    public R delete(@RequestBody Long[] ids){
        thMaintenanceNumberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
