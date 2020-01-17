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

import com.ags.modules.sys.entity.ThAutoConfigEntity;
import com.ags.modules.sys.service.ThAutoConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 自动化看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:12:54
 */
@RestController
@RequestMapping("sys/thautoconfig")
public class ThAutoConfigController {
    @Autowired
    private ThAutoConfigService thAutoConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thautoconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thAutoConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thautoconfig:info")
    public R info(@PathVariable("id") Long id){
        ThAutoConfigEntity thAutoConfig = thAutoConfigService.getById(id);

        return R.ok().put("thAutoConfig", thAutoConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thautoconfig:save")
    public R save(@RequestBody ThAutoConfigEntity thAutoConfig){

        //只允许新增一条

        if(thAutoConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }


        thAutoConfigService.save(thAutoConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thautoconfig:update")
    public R update(@RequestBody ThAutoConfigEntity thAutoConfig){
        ValidatorUtils.validateEntity(thAutoConfig);
        thAutoConfigService.updateById(thAutoConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thautoconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thAutoConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
