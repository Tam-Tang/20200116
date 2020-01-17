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

import com.ags.modules.sys.entity.ThProductUphEntity;
import com.ags.modules.sys.service.ThProductUphService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 产线标准产出信息表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@RestController
@RequestMapping("sys/thproductuph")
public class ThProductUphController {
    @Autowired
    private ThProductUphService thProductUphService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thproductuph:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thProductUphService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thproductuph:info")
    public R info(@PathVariable("id") String id){
        ThProductUphEntity thProductUph = thProductUphService.getById(id);

        return R.ok().put("thProductUph", thProductUph);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thproductuph:save")
    public R save(@RequestBody ThProductUphEntity thProductUph){
        thProductUphService.save(thProductUph);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thproductuph:update")
    public R update(@RequestBody ThProductUphEntity thProductUph){
        ValidatorUtils.validateEntity(thProductUph);
        thProductUphService.updateById(thProductUph);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thproductuph:delete")
    public R delete(@RequestBody String[] ids){
        thProductUphService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
