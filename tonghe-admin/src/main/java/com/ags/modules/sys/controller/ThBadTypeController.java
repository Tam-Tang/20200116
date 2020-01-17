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

import com.ags.modules.sys.entity.ThBadTypeEntity;
import com.ags.modules.sys.service.ThBadTypeService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 不良类别信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 11:49:26
 */
@RestController
@RequestMapping("sys/thbadtype")
public class ThBadTypeController {
    @Autowired
    private ThBadTypeService thBadTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thbadtype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thBadTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thbadtype:info")
    public R info(@PathVariable("id") Long id){
        ThBadTypeEntity thBadType = thBadTypeService.getById(id);

        return R.ok().put("thBadType", thBadType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thbadtype:save")
    public R save(@RequestBody ThBadTypeEntity thBadType){

        //只允许新增一条
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("BAD_TYPE_ID",thBadType.getBadTypeId());

        if(thBadTypeService.list(queryWrapper).size()==1){
            return R.error("该编号已经存在");
        }



        thBadTypeService.save(thBadType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thbadtype:update")
    public R update(@RequestBody ThBadTypeEntity thBadType){
        ValidatorUtils.validateEntity(thBadType);
        thBadTypeService.updateById(thBadType);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thbadtype:delete")
    public R delete(@RequestBody Long[] ids){
        thBadTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
