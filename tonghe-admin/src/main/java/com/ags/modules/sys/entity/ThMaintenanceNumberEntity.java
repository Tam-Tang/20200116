package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 维修数量表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_MAINTENANCE_NUMBER")
public class ThMaintenanceNumberEntity implements Serializable {
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
	 * 日期
	 */
	private Date dataTime;
	/**
	 * 班别
	 */
	private String shiftType;
	/**
	 * 待维修数量
	 */
	private String maintainQty;
	/**
	 * 已维修数量
	 */
	private String finishQty;
	/**
	 * 维修工段
	 */
	private String maintainStep;

}
