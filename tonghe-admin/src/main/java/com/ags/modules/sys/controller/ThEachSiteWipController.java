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

import com.ags.modules.sys.entity.ThEachSiteWipEntity;
import com.ags.modules.sys.service.ThEachSiteWipService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 各站点WIP
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/theachsitewip")
public class ThEachSiteWipController {
    @Autowired
    private ThEachSiteWipService thEachSiteWipService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:theachsitewip:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thEachSiteWipService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:theachsitewip:info")
    public R info(@PathVariable("id") Long id){
        ThEachSiteWipEntity thEachSiteWip = thEachSiteWipService.getById(id);

        return R.ok().put("thEachSiteWip", thEachSiteWip);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:theachsitewip:save")
    public R save(@RequestBody ThEachSiteWipEntity thEachSiteWip){
        thEachSiteWipService.save(thEachSiteWip);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:theachsitewip:update")
    public R update(@RequestBody ThEachSiteWipEntity thEachSiteWip){
        ValidatorUtils.validateEntity(thEachSiteWip);
        thEachSiteWipService.updateById(thEachSiteWip);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:theachsitewip:delete")
    public R delete(@RequestBody Long[] ids){
        thEachSiteWipService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
