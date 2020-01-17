package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 不良类别信息表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 11:49:26
 */
@Data
@TableName("TH_BAD_TYPE")
public class ThBadTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 不良类别ID
	 */
	private String badTypeId;
	/**
	 * 不良类别名称
	 */
	private String badTypeName;

	/**
	 * 类别(制程段)
	 */
	private String category;

	private String createdBy;
	private Date createdTime;
	private String updatedBy;
	private Date  updatedTime;


}
