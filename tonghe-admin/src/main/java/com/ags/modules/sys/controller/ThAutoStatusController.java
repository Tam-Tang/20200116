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

import com.ags.modules.sys.entity.ThAutoStatusEntity;
import com.ags.modules.sys.service.ThAutoStatusService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * ${comments}
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 12:55:25
 */
@RestController
@RequestMapping("sys/thautostatus")
public class ThAutoStatusController {
    @Autowired
    private ThAutoStatusService thAutoStatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thautostatus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thAutoStatusService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thautostatus:info")
    public R info(@PathVariable("id") Long id){
        ThAutoStatusEntity thAutoStatus = thAutoStatusService.getById(id);

        return R.ok().put("thAutoStatus", thAutoStatus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thautostatus:save")
    public R save(@RequestBody ThAutoStatusEntity thAutoStatus){
        thAutoStatusService.save(thAutoStatus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thautostatus:update")
    public R update(@RequestBody ThAutoStatusEntity thAutoStatus){
        ValidatorUtils.validateEntity(thAutoStatus);
        thAutoStatusService.updateById(thAutoStatus);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thautostatus:delete")
    public R delete(@RequestBody Long[] ids){
        thAutoStatusService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
