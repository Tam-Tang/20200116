package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产线基础信息表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:59
 */
@Data
@TableName("TH_PRODUCT_LINE")
public class ThProductLineEntity implements Serializable {
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
	 * 产线名称
	 */
	private String lineName;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date  updatedTime;

}
