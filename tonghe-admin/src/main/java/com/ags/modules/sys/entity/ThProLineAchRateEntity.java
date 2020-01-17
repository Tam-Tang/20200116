package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产线达成率
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:59:00
 */
@Data
@TableName("TH_PRO_LINE_ACH_RATE")
public class ThProLineAchRateEntity implements Serializable {
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
	 * 线别
	 */
	private String lineId;
	/**
	 * 班别
	 */
	private String shiftName;
	/**
	 * 0前段  1后段
	 */
	private String step;
	/**
	 * 生产排产量
	 */
	private String schedulQty;
	/**
	 * 前段无值，后段生产，报表系统维护
	 */
	private String standardQry;
	/**
	 * 生产产出量
	 */
	private String actualQty;
	/**
	 * 前段有值，后段需要报表系统自己计算
	 */
	private String achRate;
	private String category;
	private String resonDesc;

}
