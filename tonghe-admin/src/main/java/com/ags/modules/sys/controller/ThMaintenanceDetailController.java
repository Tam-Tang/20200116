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

import com.ags.modules.sys.entity.ThMaintenanceDetailEntity;
import com.ags.modules.sys.service.ThMaintenanceDetailService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 维修明细表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:12:57
 */
@RestController
@RequestMapping("sys/thmaintenancedetail")
public class ThMaintenanceDetailController {
    @Autowired
    private ThMaintenanceDetailService thMaintenanceDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thmaintenancedetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thMaintenanceDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thmaintenancedetail:info")
    public R info(@PathVariable("id") Long id){
        ThMaintenanceDetailEntity thMaintenanceDetail = thMaintenanceDetailService.getById(id);

        return R.ok().put("thMaintenanceDetail", thMaintenanceDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thmaintenancedetail:save")
    public R save(@RequestBody ThMaintenanceDetailEntity thMaintenanceDetail){
        thMaintenanceDetailService.save(thMaintenanceDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thmaintenancedetail:update")
    public R update(@RequestBody ThMaintenanceDetailEntity thMaintenanceDetail){
        ValidatorUtils.validateEntity(thMaintenanceDetail);
        thMaintenanceDetailService.updateById(thMaintenanceDetail);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thmaintenancedetail:delete")
    public R delete(@RequestBody Long[] ids){
        thMaintenanceDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
