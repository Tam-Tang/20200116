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

import com.ags.modules.sys.entity.ThRateRedDataEntity;
import com.ags.modules.sys.service.ThRateRedDataService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 達成率警戒值配置
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-02 15:41:24
 */
@RestController
@RequestMapping("sys/thratereddata")
public class ThRateRedDataController {
    @Autowired
    private ThRateRedDataService thRateRedDataService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thratereddata:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thRateRedDataService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thratereddata:info")
    public R info(@PathVariable("id") Long id){
        ThRateRedDataEntity thRateRedData = thRateRedDataService.getById(id);

        return R.ok().put("thRateRedData", thRateRedData);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thratereddata:save")
    public R save(@RequestBody ThRateRedDataEntity thRateRedData){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("LINE_ID",thRateRedData.getLineId());
        if(thRateRedDataService.list(queryWrapper).size()==1){
            return R.error("相同线别只能维护一条信息!请在原先的数据上面修改");
        }

        thRateRedDataService.save(thRateRedData);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thratereddata:update")
    public R update(@RequestBody ThRateRedDataEntity thRateRedData){
        ValidatorUtils.validateEntity(thRateRedData);
        thRateRedDataService.updateById(thRateRedData);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thratereddata:delete")
    public R delete(@RequestBody Long[] ids){
        thRateRedDataService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
