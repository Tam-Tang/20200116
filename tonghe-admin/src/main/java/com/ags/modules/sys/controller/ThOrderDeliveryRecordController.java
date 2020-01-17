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

import com.ags.modules.sys.entity.ThOrderDeliveryRecordEntity;
import com.ags.modules.sys.service.ThOrderDeliveryRecordService;
import com.ags.common.utils.PageUtils;
import com.ags.common.utils.R;



/**
 * 订单交付状况记录表（订单单位）
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 16:13:02
 */
@RestController
@RequestMapping("sys/thorderdeliveryrecord")
public class ThOrderDeliveryRecordController {
    @Autowired
    private ThOrderDeliveryRecordService thOrderDeliveryRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:thorderdeliveryrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = thOrderDeliveryRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:thorderdeliveryrecord:info")
    public R info(@PathVariable("id") Long id){
        ThOrderDeliveryRecordEntity thOrderDeliveryRecord = thOrderDeliveryRecordService.getById(id);

        return R.ok().put("thOrderDeliveryRecord", thOrderDeliveryRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:thorderdeliveryrecord:save")
    public R save(@RequestBody ThOrderDeliveryRecordEntity thOrderDeliveryRecord){
        thOrderDeliveryRecordService.save(thOrderDeliveryRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:thorderdeliveryrecord:update")
    public R update(@RequestBody ThOrderDeliveryRecordEntity thOrderDeliveryRecord){
        ValidatorUtils.validateEntity(thOrderDeliveryRecord);
        thOrderDeliveryRecordService.updateById(thOrderDeliveryRecord);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:thorderdeliveryrecord:delete")
    public R delete(@RequestBody Long[] ids){
        thOrderDeliveryRecordService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
