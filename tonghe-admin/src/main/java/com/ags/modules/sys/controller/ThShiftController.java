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

import com.ags.modules.sys.entity.ThShiftEntity;
import com.ags.modules.sys.service.ThShiftService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 班别信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-11 16:35:07
 */
@RestController
@RequestMapping("sys/thshift")
public class ThShiftController {
    @Autowired
    private ThShiftService thShiftService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thshift:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thShiftService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thshift:info")
    public R info(@PathVariable("id") String id){
        ThShiftEntity thShift = thShiftService.getById(id);

        return R.ok().put("thShift", thShift);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thshift:save")
    public R save(@RequestBody ThShiftEntity thShift){
        thShiftService.save(thShift);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thshift:update")
    public R update(@RequestBody ThShiftEntity thShift){
        ValidatorUtils.validateEntity(thShift);
        thShiftService.updateById(thShift);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thshift:delete")
    public R delete(@RequestBody String[] ids){
        thShiftService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
