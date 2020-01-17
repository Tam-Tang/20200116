package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单看板配置表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:59
 */
@Data
@TableName("TH_ORDER_CONFIG")
public class ThOrderConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 生产周期警戒值时长
	 */
	private String redResult;
	/**
	 *交付周期達標率(%)
	 */
	private String complianceRate;
	/**
	 *交付周期(天)
	 */
	private String complianceDay;
	/**
	 * 推送邮箱(;分隔)
	 */
	private String email;
	/**
	 * 刷新间隔
	 */
	private String refreshTime;
	/**
	 * 超时时段显示颜色
	 */
	private String timeoutColor;

}
