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

import com.ags.modules.sys.entity.ThMachineThrowingRateEntity;
import com.ags.modules.sys.service.ThMachineThrowingRateService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 机台抛料率
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thmachinethrowingrate")
public class ThMachineThrowingRateController {
    @Autowired
    private ThMachineThrowingRateService thMachineThrowingRateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thmachinethrowingrate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thMachineThrowingRateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thmachinethrowingrate:info")
    public R info(@PathVariable("id") Long id){
        ThMachineThrowingRateEntity thMachineThrowingRate = thMachineThrowingRateService.getById(id);

        return R.ok().put("thMachineThrowingRate", thMachineThrowingRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thmachinethrowingrate:save")
    public R save(@RequestBody ThMachineThrowingRateEntity thMachineThrowingRate){
        thMachineThrowingRateService.save(thMachineThrowingRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thmachinethrowingrate:update")
    public R update(@RequestBody ThMachineThrowingRateEntity thMachineThrowingRate){
        ValidatorUtils.validateEntity(thMachineThrowingRate);
        thMachineThrowingRateService.updateById(thMachineThrowingRate);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thmachinethrowingrate:delete")
    public R delete(@RequestBody Long[] ids){
        thMachineThrowingRateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
