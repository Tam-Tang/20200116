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

import com.ags.modules.sys.entity.ThEsdRealTimeEntity;
import com.ags.modules.sys.service.ThEsdRealTimeService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * ESD实时表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thesdrealtime")
public class ThEsdRealTimeController {
    @Autowired
    private ThEsdRealTimeService thEsdRealTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thesdrealtime:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thEsdRealTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thesdrealtime:info")
    public R info(@PathVariable("id") Long id){
        ThEsdRealTimeEntity thEsdRealTime = thEsdRealTimeService.getById(id);

        return R.ok().put("thEsdRealTime", thEsdRealTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thesdrealtime:save")
    public R save(@RequestBody ThEsdRealTimeEntity thEsdRealTime){
        thEsdRealTimeService.save(thEsdRealTime);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thesdrealtime:update")
    public R update(@RequestBody ThEsdRealTimeEntity thEsdRealTime){
        ValidatorUtils.validateEntity(thEsdRealTime);
        thEsdRealTimeService.updateById(thEsdRealTime);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thesdrealtime:delete")
    public R delete(@RequestBody Long[] ids){
        thEsdRealTimeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
