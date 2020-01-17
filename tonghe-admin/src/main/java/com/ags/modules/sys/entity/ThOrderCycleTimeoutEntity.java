package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单生产周期超时记录
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:59
 */
@Data
@TableName("TH_ORDER_CYCLE_TIMEOUT")
public class ThOrderCycleTimeoutEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private String id;
	/**
	 * 插入时间
	 */
	private Date createTime;
	/**
	 * 超时值
	 */
	private String overTime;
	/**
	 * 工单号
	 */
	private String orderNumber;
	/**
	 * 销售订单号
	 */
	private String saleNumber;
	/**
	 * 是否推送
	 */
	private String isPush;
	/**
	 * 推送时间
	 */
	private Date pushTime;

}
