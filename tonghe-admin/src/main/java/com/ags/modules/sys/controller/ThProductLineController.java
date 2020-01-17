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

import com.ags.modules.sys.entity.ThProductLineEntity;
import com.ags.modules.sys.service.ThProductLineService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 产线基础信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/thproductline")
public class ThProductLineController {
    @Autowired
    private ThProductLineService thProductLineService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thproductline:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thProductLineService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thproductline:info")
    public R info(@PathVariable("id") Long id){
        ThProductLineEntity thProductLine = thProductLineService.getById(id);

        return R.ok().put("thProductLine", thProductLine);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thproductline:save")
    public R save(@RequestBody ThProductLineEntity thProductLine){

        //只允许新增一条
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("LINE_ID",thProductLine.getLineId());

        if(thProductLineService.list(queryWrapper).size()==1){
            return R.error("该产线已经存在！");
        }
        thProductLineService.save(thProductLine);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thproductline:update")
    public R update(@RequestBody ThProductLineEntity thProductLine){
        ValidatorUtils.validateEntity(thProductLine);
        thProductLineService.updateById(thProductLine);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thproductline:delete")
    public R delete(@RequestBody Long[] ids){
        thProductLineService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
