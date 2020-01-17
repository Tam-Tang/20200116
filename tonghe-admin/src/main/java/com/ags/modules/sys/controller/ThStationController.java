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

import com.ags.modules.sys.entity.ThStationEntity;
import com.ags.modules.sys.service.ThStationService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 站点基础信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thstation")
public class ThStationController {
    @Autowired
    private ThStationService thStationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thstation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thStationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thstation:info")
    public R info(@PathVariable("id") Long id){
        ThStationEntity thStation = thStationService.getById(id);

        return R.ok().put("thStation", thStation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thstation:save")
    public R save(@RequestBody ThStationEntity thStation){
        thStationService.save(thStation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thstation:update")
    public R update(@RequestBody ThStationEntity thStation){
        ValidatorUtils.validateEntity(thStation);
        thStationService.updateById(thStation);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thstation:delete")
    public R delete(@RequestBody Long[] ids){
        thStationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
