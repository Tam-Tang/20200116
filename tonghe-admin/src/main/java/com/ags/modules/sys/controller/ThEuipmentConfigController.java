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

import com.ags.modules.sys.entity.ThEuipmentConfigEntity;
import com.ags.modules.sys.service.ThEuipmentConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 工序看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:00
 */
@RestController
@RequestMapping("sys/theuipmentconfig")
public class ThEuipmentConfigController {
    @Autowired
    private ThEuipmentConfigService thEuipmentConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:theuipmentconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thEuipmentConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:theuipmentconfig:info")
    public R info(@PathVariable("id") Long id){
        ThEuipmentConfigEntity thEuipmentConfig = thEuipmentConfigService.getById(id);

        return R.ok().put("thEuipmentConfig", thEuipmentConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:theuipmentconfig:save")
    public R save(@RequestBody ThEuipmentConfigEntity thEuipmentConfig){

        if(thEuipmentConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }
        thEuipmentConfigService.save(thEuipmentConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:theuipmentconfig:update")
    public R update(@RequestBody ThEuipmentConfigEntity thEuipmentConfig){
        ValidatorUtils.validateEntity(thEuipmentConfig);
        thEuipmentConfigService.updateById(thEuipmentConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:theuipmentconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thEuipmentConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
