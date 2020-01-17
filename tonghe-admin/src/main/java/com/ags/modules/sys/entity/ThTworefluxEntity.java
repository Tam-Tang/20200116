package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 二次回流數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:10
 */
@Data
@TableName("TH_TWOREFLUX")
public class ThTworefluxEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 數據ID
	 */
	@TableId
	private String id;
	/**
	 * 員工編號
	 */
	private String staffId;
	/**
	 * 員工姓名
	 */
	private String staffName;
	/**
	 * 維修數量
	 */
	private String fixNum;
	/**
	 * 回流數量
	 */
	private String refluxNum;
	/**
	 * 回流率
	 */
	private String refluxRate;
	/**
	 * 日期
	 */
	private Date dataTime;
	/**
	 * 插入時間
	 */
	private Date createTime;

}
