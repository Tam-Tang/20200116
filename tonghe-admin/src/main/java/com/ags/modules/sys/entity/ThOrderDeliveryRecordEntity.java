package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单交付状况记录表（订单单位）
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:59
 */
@Data
@TableName("TH_ORDER_DELIVERY_RECORD")
public class ThOrderDeliveryRecordEntity implements Serializable {
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
	 * 订单总数量
	 */
	private String orderTotalQty;
	/**
	 * 订单已交付数量
	 */
	private String orderDeliveryQty;
	/**
	 * 订单未交付数量
	 */
	private String orderUndeliveryQty;
	/**
	 * 目标交付数量
	 */
	private String ordeqTy;

}
