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

import com.ags.modules.sys.entity.ThUndeliveredDetailsEntity;
import com.ags.modules.sys.service.ThUndeliveredDetailsService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 未交付明细表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thundelivereddetails")
public class ThUndeliveredDetailsController {
    @Autowired
    private ThUndeliveredDetailsService thUndeliveredDetailsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thundelivereddetails:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thUndeliveredDetailsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thundelivereddetails:info")
    public R info(@PathVariable("id") Long id){
        ThUndeliveredDetailsEntity thUndeliveredDetails = thUndeliveredDetailsService.getById(id);

        return R.ok().put("thUndeliveredDetails", thUndeliveredDetails);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thundelivereddetails:save")
    public R save(@RequestBody ThUndeliveredDetailsEntity thUndeliveredDetails){
        thUndeliveredDetailsService.save(thUndeliveredDetails);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thundelivereddetails:update")
    public R update(@RequestBody ThUndeliveredDetailsEntity thUndeliveredDetails){
        ValidatorUtils.validateEntity(thUndeliveredDetails);
        thUndeliveredDetailsService.updateById(thUndeliveredDetails);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thundelivereddetails:delete")
    public R delete(@RequestBody Long[] ids){
        thUndeliveredDetailsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
