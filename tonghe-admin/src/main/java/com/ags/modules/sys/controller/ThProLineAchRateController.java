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

import com.ags.modules.sys.entity.ThProLineAchRateEntity;
import com.ags.modules.sys.service.ThProLineAchRateService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 产线达成率
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thprolineachrate")
public class ThProLineAchRateController {
    @Autowired
    private ThProLineAchRateService thProLineAchRateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thprolineachrate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thProLineAchRateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thprolineachrate:info")
    public R info(@PathVariable("id") Long id){
        ThProLineAchRateEntity thProLineAchRate = thProLineAchRateService.getById(id);

        return R.ok().put("thProLineAchRate", thProLineAchRate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thprolineachrate:save")
    public R save(@RequestBody ThProLineAchRateEntity thProLineAchRate){
        thProLineAchRateService.save(thProLineAchRate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thprolineachrate:update")
    public R update(@RequestBody ThProLineAchRateEntity thProLineAchRate){
        ValidatorUtils.validateEntity(thProLineAchRate);
        thProLineAchRateService.updateById(thProLineAchRate);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thprolineachrate:delete")
    public R delete(@RequestBody Long[] ids){
        thProLineAchRateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
