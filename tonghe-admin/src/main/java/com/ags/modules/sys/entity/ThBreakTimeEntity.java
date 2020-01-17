package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 休息时间表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 08:38:58
 */
@Data
@TableName("TH_BREAK_TIME")
public class ThBreakTimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 休息开始时间
	 */
	private Date startTime;
	/**
	 * 休息结束时间
	 */
	private Date endTime;
	/**
	 * 线别编号
	 */
	private String lineId;
	/**
	 * 班别
	 */
	private String shiftName;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date  updatedTime;

}
