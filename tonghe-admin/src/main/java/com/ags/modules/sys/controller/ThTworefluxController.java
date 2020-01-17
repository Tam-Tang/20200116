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

import com.ags.modules.sys.entity.ThTworefluxEntity;
import com.ags.modules.sys.service.ThTworefluxService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 二次回流數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:10
 */
@RestController
@RequestMapping("sys/thtworeflux")
public class ThTworefluxController {
    @Autowired
    private ThTworefluxService thTworefluxService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thtworeflux:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thTworefluxService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thtworeflux:info")
    public R info(@PathVariable("id") Long id){
        ThTworefluxEntity thTworeflux = thTworefluxService.getById(id);

        return R.ok().put("thTworeflux", thTworeflux);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thtworeflux:save")
    public R save(@RequestBody ThTworefluxEntity thTworeflux){
        thTworefluxService.save(thTworeflux);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thtworeflux:update")
    public R update(@RequestBody ThTworefluxEntity thTworeflux){
        ValidatorUtils.validateEntity(thTworeflux);
        thTworefluxService.updateById(thTworeflux);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thtworeflux:delete")
    public R delete(@RequestBody Long[] ids){
        thTworefluxService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
