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

import com.ags.modules.sys.entity.ThEsdConfigEntity;
import com.ags.modules.sys.service.ThEsdConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 温湿度ESD看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:00
 */
@RestController
@RequestMapping("sys/thesdconfig")
public class ThEsdConfigController {
    @Autowired
    private ThEsdConfigService thEsdConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thesdconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thEsdConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thesdconfig:info")
    public R info(@PathVariable("id") Long id){
        ThEsdConfigEntity thEsdConfig = thEsdConfigService.getById(id);

        return R.ok().put("thEsdConfig", thEsdConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thesdconfig:save")
    public R save(@RequestBody ThEsdConfigEntity thEsdConfig){

        if(thEsdConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }

        thEsdConfigService.save(thEsdConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thesdconfig:update")
    public R update(@RequestBody ThEsdConfigEntity thEsdConfig){
        ValidatorUtils.validateEntity(thEsdConfig);
        thEsdConfigService.updateById(thEsdConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thesdconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thEsdConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
