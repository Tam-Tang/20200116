package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 各站点WIP
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:59:00
 */
@Data
@TableName("TH_EACH_SITE_WIP")
public class ThEachSiteWipEntity implements Serializable {
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
	 * 站点
	 */
	private String stationId;
	/**
	 * 班别
	 */
	private String shiftName;
	/**
	 * 数量
	 */
	private String wipQty;

}
