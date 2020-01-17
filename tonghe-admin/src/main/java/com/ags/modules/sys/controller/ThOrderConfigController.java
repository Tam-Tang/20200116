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

import com.ags.modules.sys.entity.ThOrderConfigEntity;
import com.ags.modules.sys.service.ThOrderConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 订单看板配置表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thorderconfig")
public class ThOrderConfigController {
    @Autowired
    private ThOrderConfigService thOrderConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thorderconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thOrderConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thorderconfig:info")
    public R info(@PathVariable("id") Long id){
        ThOrderConfigEntity thOrderConfig = thOrderConfigService.getById(id);

        return R.ok().put("thOrderConfig", thOrderConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thorderconfig:save")
    public R save(@RequestBody ThOrderConfigEntity thOrderConfig){
        thOrderConfigService.save(thOrderConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thorderconfig:update")
    public R update(@RequestBody ThOrderConfigEntity thOrderConfig){
        ValidatorUtils.validateEntity(thOrderConfig);
        thOrderConfigService.updateById(thOrderConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thorderconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thOrderConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
