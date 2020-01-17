package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-06 12:55:25
 */
@Data
@TableName("TH_AUTO_STATUS")
public class ThAutoStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private String id;
	/**
	 * 设备名称
	 */
	private String euipmentName;
	/**
	 * 设备类型
	 */
	private String euipmentType;
	/**
	 * 班别
	 */
	private String shiftName;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 暂停时长
	 */
	private String stopTime;
	/**
	 * 日期
	 */
	private Date dataTime;
	/**
	 * 插入时间
	 */
	private Date createTime;

}
