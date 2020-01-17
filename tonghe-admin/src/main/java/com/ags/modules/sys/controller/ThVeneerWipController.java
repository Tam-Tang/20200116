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

import com.ags.modules.sys.entity.ThVeneerWipEntity;
import com.ags.modules.sys.service.ThVeneerWipService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 單板WIP趨勢數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:09
 */
@RestController
@RequestMapping("sys/thveneerwip")
public class ThVeneerWipController {
    @Autowired
    private ThVeneerWipService thVeneerWipService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thveneerwip:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thVeneerWipService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thveneerwip:info")
    public R info(@PathVariable("id") Long id){
        ThVeneerWipEntity thVeneerWip = thVeneerWipService.getById(id);

        return R.ok().put("thVeneerWip", thVeneerWip);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thveneerwip:save")
    public R save(@RequestBody ThVeneerWipEntity thVeneerWip){
        thVeneerWipService.save(thVeneerWip);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thveneerwip:update")
    public R update(@RequestBody ThVeneerWipEntity thVeneerWip){
        ValidatorUtils.validateEntity(thVeneerWip);
        thVeneerWipService.updateById(thVeneerWip);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thveneerwip:delete")
    public R delete(@RequestBody Long[] ids){
        thVeneerWipService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
