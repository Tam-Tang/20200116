package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备运行状态表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_EUIPMENT_STATUS")
public class ThEuipmentStatusEntity implements Serializable {
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
	 * 设备ID
	 */
	private String euipmentId;
	/**
	 * 设备名称
	 */
	private String euipmentName;
	/**
	 * 设备类型
	 */
	private String euipmentType;
	/**
	 * 线体
	 */
	private String lineId;
	/**
	 * 班别
	 */
	private String shiftName;
	/**
	 * 状态
	 */
	private String status;


}
