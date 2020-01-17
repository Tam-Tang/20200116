

package com.ags.service;

import com.ags.entity.TokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户Token
 *

 */
public interface TokenService extends IService<TokenEntity> {

	TokenEntity queryByToken(String token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token信息
	 */
	TokenEntity createToken(long userId);

	/**
	 * 设置token过期
	 * @param userId 用户ID
	 */
	void expireToken(long userId);

}
