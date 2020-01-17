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

import com.ags.modules.sys.entity.ThEuipmentStatusEntity;
import com.ags.modules.sys.service.ThEuipmentStatusService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 设备运行状态表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:00
 */
@RestController
@RequestMapping("sys/theuipmentstatus")
public class ThEuipmentStatusController {
    @Autowired
    private ThEuipmentStatusService thEuipmentStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:theuipmentstatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thEuipmentStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:theuipmentstatus:info")
    public R info(@PathVariable("id") Long id){
        ThEuipmentStatusEntity thEuipmentStatus = thEuipmentStatusService.getById(id);

        return R.ok().put("thEuipmentStatus", thEuipmentStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:theuipmentstatus:save")
    public R save(@RequestBody ThEuipmentStatusEntity thEuipmentStatus){
        thEuipmentStatusService.save(thEuipmentStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:theuipmentstatus:update")
    public R update(@RequestBody ThEuipmentStatusEntity thEuipmentStatus){
        ValidatorUtils.validateEntity(thEuipmentStatus);
        thEuipmentStatusService.updateById(thEuipmentStatus);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:theuipmentstatus:delete")
    public R delete(@RequestBody Long[] ids){
        thEuipmentStatusService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
