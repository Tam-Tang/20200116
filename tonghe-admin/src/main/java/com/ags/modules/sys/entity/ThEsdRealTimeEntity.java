package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ESD实时表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_ESD_REAL_TIME")
public class ThEsdRealTimeEntity implements Serializable {
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
	 * 状态
	 */
	private String esdStatus;
	/**
	 * 是否推送
	 */
	private String isPush;
	/**
	 * 推送时间
	 */
	private Date pushTime;

}
