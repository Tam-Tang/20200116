package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 维修看板配置
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:54
 */
@Data
@TableName("TH_MAINTENANCE_CONFIG")
public class ThMaintenanceConfigEntity implements Serializable {
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
	private String standardData;
	private String twoRefluxStandardData;
	private String wipStandardData;

}
