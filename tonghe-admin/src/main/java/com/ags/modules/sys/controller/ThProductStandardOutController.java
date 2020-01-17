package com.ags.modules.sys.controller;

import java.text.SimpleDateFormat;
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

import com.ags.modules.sys.entity.ThProductStandardOutEntity;
import com.ags.modules.sys.service.ThProductStandardOutService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 產線標準產量表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-05 14:30:56
 */
@RestController
@RequestMapping("sys/thproductstandardout")
public class ThProductStandardOutController {
    @Autowired
    private ThProductStandardOutService thProductStandardOutService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thproductstandardout:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thProductStandardOutService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thproductstandardout:info")
    public R info(@PathVariable("id") Long id){
        ThProductStandardOutEntity thProductStandardOut = thProductStandardOutService.getById(id);

        return R.ok().put("thProductStandardOut", thProductStandardOut);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thproductstandardout:save")
    public R save(@RequestBody ThProductStandardOutEntity thProductStandardOut){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTimeStr = sdf.format(thProductStandardOut.getStartTime());
        String endTimeStr = sdf.format(thProductStandardOut.getEndTime());
        //只允许新增一条
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("LINE_ID",thProductStandardOut.getLineId());
        queryWrapper.apply("to_date({0},'yyyy-mm-dd hh24:mi:ss') >= START_TIME",startTimeStr);
        queryWrapper.apply("to_date({0},'yyyy-mm-dd hh24:mi:ss') <= END_TIME",endTimeStr);

        if(thProductStandardOutService.list(queryWrapper).size()>=1){
            return R.error("该产线产量已经存在！");
        }
        thProductStandardOutService.save(thProductStandardOut);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thproductstandardout:update")
    public R update(@RequestBody ThProductStandardOutEntity thProductStandardOut){
        ValidatorUtils.validateEntity(thProductStandardOut);
        thProductStandardOutService.updateById(thProductStandardOut);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thproductstandardout:delete")
    public R delete(@RequestBody Long[] ids){
        thProductStandardOutService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
