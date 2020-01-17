package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 节假日表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_HOLIDAY")
public class ThHolidayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 节假日
	 */
	private Date holidayTime;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date  updatedTime;

}
