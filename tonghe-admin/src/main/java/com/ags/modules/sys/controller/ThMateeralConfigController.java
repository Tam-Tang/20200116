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

import com.ags.modules.sys.entity.ThMateeralConfigEntity;
import com.ags.modules.sys.service.ThMateeralConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 抛料看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thmateeralconfig")
public class ThMateeralConfigController {
    @Autowired
    private ThMateeralConfigService thMateeralConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thmateeralconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thMateeralConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thmateeralconfig:info")
    public R info(@PathVariable("id") String id){
        ThMateeralConfigEntity thMateeralConfig = thMateeralConfigService.getById(id);

        return R.ok().put("thMateeralConfig", thMateeralConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thmateeralconfig:save")
    public R save(@RequestBody ThMateeralConfigEntity thMateeralConfig){

        if(thMateeralConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }

        thMateeralConfigService.save(thMateeralConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thmateeralconfig:update")
    public R update(@RequestBody ThMateeralConfigEntity thMateeralConfig){
        ValidatorUtils.validateEntity(thMateeralConfig);
        thMateeralConfigService.updateById(thMateeralConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thmateeralconfig:delete")
    public R delete(@RequestBody String[] ids){
        thMateeralConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
