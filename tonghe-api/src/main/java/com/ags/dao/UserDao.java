

package com.ags.dao;

import com.ags.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *

 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
