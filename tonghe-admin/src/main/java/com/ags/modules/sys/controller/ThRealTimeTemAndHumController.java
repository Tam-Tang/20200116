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

import com.ags.modules.sys.entity.ThRealTimeTemAndHumEntity;
import com.ags.modules.sys.service.ThRealTimeTemAndHumService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 温湿度实时表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:01
 */
@RestController
@RequestMapping("sys/threaltimetemandhum")
public class ThRealTimeTemAndHumController {
    @Autowired
    private ThRealTimeTemAndHumService thRealTimeTemAndHumService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:threaltimetemandhum:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thRealTimeTemAndHumService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:threaltimetemandhum:info")
    public R info(@PathVariable("id") Long id){
        ThRealTimeTemAndHumEntity thRealTimeTemAndHum = thRealTimeTemAndHumService.getById(id);

        return R.ok().put("thRealTimeTemAndHum", thRealTimeTemAndHum);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:threaltimetemandhum:save")
    public R save(@RequestBody ThRealTimeTemAndHumEntity thRealTimeTemAndHum){
        thRealTimeTemAndHumService.save(thRealTimeTemAndHum);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:threaltimetemandhum:update")
    public R update(@RequestBody ThRealTimeTemAndHumEntity thRealTimeTemAndHum){
        ValidatorUtils.validateEntity(thRealTimeTemAndHum);
        thRealTimeTemAndHumService.updateById(thRealTimeTemAndHum);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:threaltimetemandhum:delete")
    public R delete(@RequestBody Long[] ids){
        thRealTimeTemAndHumService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
