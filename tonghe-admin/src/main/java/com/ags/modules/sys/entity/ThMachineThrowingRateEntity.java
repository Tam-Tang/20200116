package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机台抛料率
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:58
 */
@Data
@TableName("TH_MACHINE_THROWING_RATE")
public class ThMachineThrowingRateEntity implements Serializable {
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
	 * 轨道编号
	 */
	private String trackNo;

	/**
	 * 机台编号
	 */
	private String machineNo;
	/**
	 * 班别
	 */
	private String shiftName;
	/**
	 * 物料编号
	 */
	private String partNumber;
	/**
	 * 置件数量
	 */
	private String setQty;
	/**
	 * 抛料数量
	 */
	private String throwQty;
	/**
	 * 抛料率
	 */
	private String throwRate;


}
