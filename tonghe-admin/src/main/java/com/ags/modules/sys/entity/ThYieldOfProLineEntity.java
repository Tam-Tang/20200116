package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产线良率表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 10:08:54
 */
@Data
@TableName("TH_YIELD_OF_PRO_LINE")
public class ThYieldOfProLineEntity implements Serializable {
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
	 * 产线编号
	 */
	private String productLineId;
	/**
	 * 站点编号
	 */
	private String stationId;
	/**
	 * 良率
	 */
	private String goodRate;
	/**
	 * 班别
	 */
	private String shiftId;

}
