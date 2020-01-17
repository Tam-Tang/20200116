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

import com.ags.modules.sys.entity.ThMaintenanceConfigEntity;
import com.ags.modules.sys.service.ThMaintenanceConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 维修看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:12:57
 */
@RestController
@RequestMapping("sys/thmaintenanceconfig")
public class ThMaintenanceConfigController {
    @Autowired
    private ThMaintenanceConfigService thMaintenanceConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thmaintenanceconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thMaintenanceConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thmaintenanceconfig:info")
    public R info(@PathVariable("id") Long id){
        ThMaintenanceConfigEntity thMaintenanceConfig = thMaintenanceConfigService.getById(id);

        return R.ok().put("thMaintenanceConfig", thMaintenanceConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thmaintenanceconfig:save")
    public R save(@RequestBody ThMaintenanceConfigEntity thMaintenanceConfig){

        if(thMaintenanceConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }

        thMaintenanceConfigService.save(thMaintenanceConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thmaintenanceconfig:update")
    public R update(@RequestBody ThMaintenanceConfigEntity thMaintenanceConfig){
        ValidatorUtils.validateEntity(thMaintenanceConfig);
        thMaintenanceConfigService.updateById(thMaintenanceConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thmaintenanceconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thMaintenanceConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
