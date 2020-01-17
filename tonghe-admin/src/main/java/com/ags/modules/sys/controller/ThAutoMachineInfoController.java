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

import com.ags.modules.sys.entity.ThAutoMachineInfoEntity;
import com.ags.modules.sys.service.ThAutoMachineInfoService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 設備基本信息數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-29 10:43:53
 */
@RestController
@RequestMapping("sys/thautomachineinfo")
public class ThAutoMachineInfoController {
    @Autowired
    private ThAutoMachineInfoService thAutoMachineInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thautomachineinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thAutoMachineInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thautomachineinfo:info")
    public R info(@PathVariable("id") Long id){
        ThAutoMachineInfoEntity thAutoMachineInfo = thAutoMachineInfoService.getById(id);

        return R.ok().put("thAutoMachineInfo", thAutoMachineInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thautomachineinfo:save")
    public R save(@RequestBody ThAutoMachineInfoEntity thAutoMachineInfo){
        thAutoMachineInfoService.save(thAutoMachineInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thautomachineinfo:update")
    public R update(@RequestBody ThAutoMachineInfoEntity thAutoMachineInfo){
        ValidatorUtils.validateEntity(thAutoMachineInfo);
        thAutoMachineInfoService.updateById(thAutoMachineInfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thautomachineinfo:delete")
    public R delete(@RequestBody Long[] ids){
        thAutoMachineInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
