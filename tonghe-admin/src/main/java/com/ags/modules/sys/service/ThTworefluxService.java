package com.ags.modules.sys.service;

import com.ags.modules.sys.vo.TwoRefluxVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ags.common.utils.PageUtils;
import com.ags.modules.sys.entity.ThTworefluxEntity;

import java.util.List;
import java.util.Map;

/**
 * 二次回流數據表
 *
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:10
 */
public interface ThTworefluxService extends IService<ThTworefluxEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TwoRefluxVO> queryTwoReflux();
}

