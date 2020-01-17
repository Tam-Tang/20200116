package com.ags.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.ags.common.validator.ValidatorUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ags.modules.sys.entity.ThHolidayEntity;
import com.ags.modules.sys.service.ThHolidayService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 节假日表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thholiday")
public class ThHolidayController {
    @Autowired
    private ThHolidayService thHolidayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thholiday:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thHolidayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thholiday:info")
    public R info(@PathVariable("id") Long id){
        ThHolidayEntity thHoliday = thHolidayService.getById(id);

        return R.ok().put("thHoliday", thHoliday);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thholiday:save")
    public R save(@RequestBody ThHolidayEntity thHoliday){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("HOLIDAY_TIME",thHoliday.getHolidayTime());

        if(thHolidayService.list(queryWrapper).size()==1){
            return R.error("该日期已经添加！");
        }

        thHolidayService.save(thHoliday);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thholiday:update")
    public R update(@RequestBody ThHolidayEntity thHoliday){
        ValidatorUtils.validateEntity(thHoliday);
        thHolidayService.updateById(thHoliday);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thholiday:delete")
    public R delete(@RequestBody Long[] ids){
        thHolidayService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
