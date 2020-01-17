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

import com.ags.modules.sys.entity.ThYieldOfProLineEntity;
import com.ags.modules.sys.service.ThYieldOfProLineService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 产线良率表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 10:08:54
 */
@RestController
@RequestMapping("sys/thyieldofproline")
public class ThYieldOfProLineController {
    @Autowired
    private ThYieldOfProLineService thYieldOfProLineService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thyieldofproline:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thYieldOfProLineService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thyieldofproline:info")
    public R info(@PathVariable("id") Long id){
        ThYieldOfProLineEntity thYieldOfProLine = thYieldOfProLineService.getById(id);

        return R.ok().put("thYieldOfProLine", thYieldOfProLine);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thyieldofproline:save")
    public R save(@RequestBody ThYieldOfProLineEntity thYieldOfProLine){
        thYieldOfProLineService.save(thYieldOfProLine);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thyieldofproline:update")
    public R update(@RequestBody ThYieldOfProLineEntity thYieldOfProLine){
        ValidatorUtils.validateEntity(thYieldOfProLine);
        thYieldOfProLineService.updateById(thYieldOfProLine);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thyieldofproline:delete")
    public R delete(@RequestBody Long[] ids){
        thYieldOfProLineService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
