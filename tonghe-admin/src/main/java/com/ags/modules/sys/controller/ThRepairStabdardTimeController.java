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

import com.ags.modules.sys.entity.ThRepairStabdardTimeEntity;
import com.ags.modules.sys.service.ThRepairStabdardTimeService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 员工维修标准工时表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:12:57
 */
@RestController
@RequestMapping("sys/threpairstabdardtime")
public class ThRepairStabdardTimeController {
    @Autowired
    private ThRepairStabdardTimeService thRepairStabdardTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:threpairstabdardtime:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thRepairStabdardTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:threpairstabdardtime:info")
    public R info(@PathVariable("id") Long id){
        ThRepairStabdardTimeEntity thRepairStabdardTime = thRepairStabdardTimeService.getById(id);

        return R.ok().put("thRepairStabdardTime", thRepairStabdardTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:threpairstabdardtime:save")
    public R save(@RequestBody ThRepairStabdardTimeEntity thRepairStabdardTime){
        thRepairStabdardTimeService.save(thRepairStabdardTime);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:threpairstabdardtime:update")
    public R update(@RequestBody ThRepairStabdardTimeEntity thRepairStabdardTime){
        ValidatorUtils.validateEntity(thRepairStabdardTime);
        thRepairStabdardTimeService.updateById(thRepairStabdardTime);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:threpairstabdardtime:delete")
    public R delete(@RequestBody Long[] ids){
        thRepairStabdardTimeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
