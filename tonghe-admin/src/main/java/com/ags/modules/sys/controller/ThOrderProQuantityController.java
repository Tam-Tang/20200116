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

import com.ags.modules.sys.entity.ThOrderProQuantityEntity;
import com.ags.modules.sys.service.ThOrderProQuantityService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 订单交付状况记录表（订单产品数量单位）
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thorderproquantity")
public class ThOrderProQuantityController {
    @Autowired
    private ThOrderProQuantityService thOrderProQuantityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thorderproquantity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thOrderProQuantityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thorderproquantity:info")
    public R info(@PathVariable("id") Long id){
        ThOrderProQuantityEntity thOrderProQuantity = thOrderProQuantityService.getById(id);

        return R.ok().put("thOrderProQuantity", thOrderProQuantity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thorderproquantity:save")
    public R save(@RequestBody ThOrderProQuantityEntity thOrderProQuantity){
        thOrderProQuantityService.save(thOrderProQuantity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thorderproquantity:update")
    public R update(@RequestBody ThOrderProQuantityEntity thOrderProQuantity){
        ValidatorUtils.validateEntity(thOrderProQuantity);
        thOrderProQuantityService.updateById(thOrderProQuantity);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thorderproquantity:delete")
    public R delete(@RequestBody Long[] ids){
        thOrderProQuantityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
