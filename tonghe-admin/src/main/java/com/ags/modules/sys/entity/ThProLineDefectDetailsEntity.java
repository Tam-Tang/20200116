package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产线不良明细表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-07 10:08:54
 */
@Data
@TableName("TH_PRO_LINE_DEFECT_DETAILS")
public class ThProLineDefectDetailsEntity implements Serializable {
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
	 * 工单号
	 */
	private String orderNumber;
	/**
	 * 产品SN
	 */
	private String unitSn;
	/**
	 * 不良类别编号
	 */
	private String badTypeId;
	/**
	 * 班别
	 */
	private String shiftId;

}
