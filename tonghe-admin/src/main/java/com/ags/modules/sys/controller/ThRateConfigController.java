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

import com.ags.modules.sys.entity.ThRateConfigEntity;
import com.ags.modules.sys.service.ThRateConfigService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 达成率看板配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thrateconfig")
public class ThRateConfigController {
    @Autowired
    private ThRateConfigService thRateConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thrateconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thRateConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thrateconfig:info")
    public R info(@PathVariable("id") Long id){
        ThRateConfigEntity thRateConfig = thRateConfigService.getById(id);

        return R.ok().put("thRateConfig", thRateConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thrateconfig:save")
    public R save(@RequestBody ThRateConfigEntity thRateConfig){

        if(thRateConfigService.list().size()==1){
            return R.error("只能维护一条配置信息!请在原先的数据上面修改");
        }
        thRateConfigService.save(thRateConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thrateconfig:update")
    public R update(@RequestBody ThRateConfigEntity thRateConfig){
        ValidatorUtils.validateEntity(thRateConfig);
        thRateConfigService.updateById(thRateConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thrateconfig:delete")
    public R delete(@RequestBody Long[] ids){
        thRateConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
