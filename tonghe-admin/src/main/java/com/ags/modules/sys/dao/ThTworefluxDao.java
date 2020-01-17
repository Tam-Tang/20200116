package com.ags.modules.sys.dao;

import com.ags.modules.sys.entity.ThTworefluxEntity;
import com.ags.modules.sys.vo.TwoRefluxVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 二次回流數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:10
 */
@Mapper
public interface ThTworefluxDao extends BaseMapper<ThTworefluxEntity> {

    List<TwoRefluxVO> queryTwoReflux();

}
