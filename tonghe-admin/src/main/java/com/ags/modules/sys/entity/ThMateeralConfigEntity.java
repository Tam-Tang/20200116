package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 抛料看板配置
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_MATEERAL_CONFIG")
public class ThMateeralConfigEntity implements Serializable {
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
	/**
	 * 1000以下按数量
	 */
	private String rateDown;
	/**
	 * 1000以上按百分比
	 */
	private String rateUp;

}
