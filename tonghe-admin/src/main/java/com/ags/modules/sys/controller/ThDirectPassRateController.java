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

import com.ags.modules.sys.entity.ThDirectPassRateEntity;
import com.ags.modules.sys.service.ThDirectPassRateService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 直通率數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:11
 */
@RestController
@RequestMapping("sys/thdirectpassrate")
public class ThDirectPassRateController {
    @Autowired
    private ThDirectPassRateService thDirectPassRateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thdirectpassrate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thDirectPassRateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thdirectpassrate:info")
    public R info(@PathVariable("id") Long id){
        ThDirectPassRateEntity thDirectPassRate = thDirectPassRateService.getById(id);

        return R.ok().put("thDirectPassRate", thDirectPassRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thdirectpassrate:save")
    public R save(@RequestBody ThDirectPassRateEntity thDirectPassRate){
        thDirectPassRateService.save(thDirectPassRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thdirectpassrate:update")
    public R update(@RequestBody ThDirectPassRateEntity thDirectPassRate){
        ValidatorUtils.validateEntity(thDirectPassRate);
        thDirectPassRateService.updateById(thDirectPassRate);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thdirectpassrate:delete")
    public R delete(@RequestBody Long[] ids){
        thDirectPassRateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
