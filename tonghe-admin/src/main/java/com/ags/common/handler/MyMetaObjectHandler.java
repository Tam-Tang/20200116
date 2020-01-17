package com.ags.common.handler;

import cn.hutool.core.date.DateTime;
import com.ags.modules.sys.shiro.ShiroUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通用字段填充器
 * @author LancCJ
 * @date 2019-09-28 15:17
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        //避免使用metaObject.setValue()
        this.setFieldValByName("createdBy", ShiroUtils.getUserEntity().getUsername(), metaObject);
        this.setFieldValByName("createdTime", DateTime.now(), metaObject);
        this.setFieldValByName("updatedBy", ShiroUtils.getUserEntity().getUsername(), metaObject);
        this.setFieldValByName("updatedTime", DateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        this.setFieldValByName("updatedBy", ShiroUtils.getUserEntity().getUsername(), metaObject);
        this.setFieldValByName("updatedTime", DateTime.now(), metaObject);

    }

    /**
     * 简体转繁体
     * @param metaObject
     */
    public void Simple2Complex(MetaObject metaObject){
        metaObject.getOriginalObject();
    }

}