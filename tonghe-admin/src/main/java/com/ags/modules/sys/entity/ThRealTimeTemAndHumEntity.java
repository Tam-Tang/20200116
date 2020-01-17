package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 温湿度实时表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_REAL_TIME_TEM_AND_HUM")
public class ThRealTimeTemAndHumEntity implements Serializable {
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
	 * 点位
	 */
	private String place;
	/**
	 * 温度值
	 */
	private String temperature;
	/**
	 * 湿度值
	 */
	private String hamidity;


}
