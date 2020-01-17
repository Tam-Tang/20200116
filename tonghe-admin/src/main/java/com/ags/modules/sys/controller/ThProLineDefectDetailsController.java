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

import com.ags.modules.sys.entity.ThProLineDefectDetailsEntity;
import com.ags.modules.sys.service.ThProLineDefectDetailsService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 产线不良明细表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 10:08:54
 */
@RestController
@RequestMapping("sys/thprolinedefectdetails")
public class ThProLineDefectDetailsController {
    @Autowired
    private ThProLineDefectDetailsService thProLineDefectDetailsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thprolinedefectdetails:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thProLineDefectDetailsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thprolinedefectdetails:info")
    public R info(@PathVariable("id") Long id){
        ThProLineDefectDetailsEntity thProLineDefectDetails = thProLineDefectDetailsService.getById(id);

        return R.ok().put("thProLineDefectDetails", thProLineDefectDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thprolinedefectdetails:save")
    public R save(@RequestBody ThProLineDefectDetailsEntity thProLineDefectDetails){
        thProLineDefectDetailsService.save(thProLineDefectDetails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thprolinedefectdetails:update")
    public R update(@RequestBody ThProLineDefectDetailsEntity thProLineDefectDetails){
        ValidatorUtils.validateEntity(thProLineDefectDetails);
        thProLineDefectDetailsService.updateById(thProLineDefectDetails);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thprolinedefectdetails:delete")
    public R delete(@RequestBody Long[] ids){
        thProLineDefectDetailsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
