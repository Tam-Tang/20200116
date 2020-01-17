package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 达成率看板配置
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:59:00
 */
@Data
@TableName("TH_RATE_CONFIG")
public class ThRateConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 推送邮箱(;分隔)
	 */
	private String email;
	/**
	 * 刷新间隔
	 */
	private String refreshTime;

}
