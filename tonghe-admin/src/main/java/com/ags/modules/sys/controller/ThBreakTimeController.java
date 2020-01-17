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

import com.ags.modules.sys.entity.ThBreakTimeEntity;
import com.ags.modules.sys.service.ThBreakTimeService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 休息时间表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 08:38:58
 */
@RestController
@RequestMapping("sys/thbreaktime")
public class ThBreakTimeController {
    @Autowired
    private ThBreakTimeService thBreakTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thbreaktime:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thBreakTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thbreaktime:info")
    public R info(@PathVariable("id") Long id){
        ThBreakTimeEntity thBreakTime = thBreakTimeService.getById(id);

        return R.ok().put("thBreakTime", thBreakTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thbreaktime:save")
    public R save(@RequestBody ThBreakTimeEntity thBreakTime){

        //TODO 校验新增的不能和已经存在重叠



        thBreakTimeService.save(thBreakTime);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thbreaktime:update")
    public R update(@RequestBody ThBreakTimeEntity thBreakTime){
        ValidatorUtils.validateEntity(thBreakTime);
        thBreakTimeService.updateById(thBreakTime);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thbreaktime:delete")
    public R delete(@RequestBody Long[] ids){
        thBreakTimeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
