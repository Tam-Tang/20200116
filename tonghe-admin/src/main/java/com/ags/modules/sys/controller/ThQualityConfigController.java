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

import com.ags.modules.sys.entity.ThQualityConfigEntity;
import com.ags.modules.sys.service.ThQualityConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 质量看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thqualityconfig")
public class ThQualityConfigController {
    @Autowired
    private ThQualityConfigService thQualityConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thqualityconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thQualityConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thqualityconfig:info")
    public R info(@PathVariable("id") Long id){
        ThQualityConfigEntity thQualityConfig = thQualityConfigService.getById(id);

        return R.ok().put("thQualityConfig", thQualityConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thqualityconfig:save")
    public R save(@RequestBody ThQualityConfigEntity thQualityConfig){

        if(thQualityConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }

        thQualityConfigService.save(thQualityConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thqualityconfig:update")
    public R update(@RequestBody ThQualityConfigEntity thQualityConfig){
        ValidatorUtils.validateEntity(thQualityConfig);
        thQualityConfigService.updateById(thQualityConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thqualityconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thQualityConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
