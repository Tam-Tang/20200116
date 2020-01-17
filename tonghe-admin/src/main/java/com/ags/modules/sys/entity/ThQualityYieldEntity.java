package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 各产线良率标准表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 09:04:35
 */
@Data
@TableName("TH_QUALITY_YIELD")
public class ThQualityYieldEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 产线编号
	 */
	private String lineId;
	/**
	 * 站点编号
	 */
	private String stationId;
	/**
	 * 良率
	 */
	private String yieldNum;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date  updatedTime;

}
