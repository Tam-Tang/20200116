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

import com.ags.modules.sys.entity.ThOrderCycleTimeoutEntity;
import com.ags.modules.sys.service.ThOrderCycleTimeoutService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 订单生产周期超时记录
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thordercycletimeout")
public class ThOrderCycleTimeoutController {
    @Autowired
    private ThOrderCycleTimeoutService thOrderCycleTimeoutService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thordercycletimeout:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thOrderCycleTimeoutService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thordercycletimeout:info")
    public R info(@PathVariable("id") Long id){
        ThOrderCycleTimeoutEntity thOrderCycleTimeout = thOrderCycleTimeoutService.getById(id);

        return R.ok().put("thOrderCycleTimeout", thOrderCycleTimeout);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thordercycletimeout:save")
    public R save(@RequestBody ThOrderCycleTimeoutEntity thOrderCycleTimeout){
        thOrderCycleTimeoutService.save(thOrderCycleTimeout);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thordercycletimeout:update")
    public R update(@RequestBody ThOrderCycleTimeoutEntity thOrderCycleTimeout){
        ValidatorUtils.validateEntity(thOrderCycleTimeout);
        thOrderCycleTimeoutService.updateById(thOrderCycleTimeout);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thordercycletimeout:delete")
    public R delete(@RequestBody Long[] ids){
        thOrderCycleTimeoutService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
