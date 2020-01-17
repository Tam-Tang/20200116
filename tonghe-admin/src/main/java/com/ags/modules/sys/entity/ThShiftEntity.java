package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 班别信息表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-11 16:35:07
 */
@Data
@TableName("TH_SHIFT")
public class ThShiftEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 工厂ID
	 */
	private String factoryId;
	/**
	 * 班次
	 */
	private String shiftName;
	/**
	 * 序号
	 */
	private String seqno;
	/**
	 * 时间段
	 */
	private String hourperiod;
	/**
	 * 未知
	 */
	private String uphratio;
	/**
	 * 开始HOUR
	 */
	private String fitsrHour;
	/**
	 * 结束HOUR
	 */
	private String lastHour;
	/**
	 * 开始时间
	 */
	private String fromTime;
	/**
	 * 结束时间
	 */
	private String toTime;
	/**
	 * 备注
	 */
	private String note;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date  updatedTime;

}
