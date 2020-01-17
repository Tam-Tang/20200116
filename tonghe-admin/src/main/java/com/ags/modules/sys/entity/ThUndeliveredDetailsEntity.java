package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 未交付明细表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:59
 */
@Data
@TableName("TH_UNDELIVERED_DETAILS")
public class ThUndeliveredDetailsEntity implements Serializable {
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
	 * 工单号
	 */
	private String orderNumber;
	/**
	 * 销售订单号
	 */
	private String saleNumber;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 未交付数量
	 */
	private String undeliveryQty;

}
