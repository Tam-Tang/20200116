package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 設備基本信息數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-29 10:43:53
 */
@Data
@TableName("TH_AUTO_MACHINE_INFO")
public class ThAutoMachineInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 數據ID
	 */
	@TableId
	private String id;
	/**
	 * 設備名稱
	 */
	private String machineName;
	/**
	 * 設備UPH
	 */
	private String uph;
	/**
	 * 設備位置
	 */
	private String position;
	/**
	 * 設備類型
	 */
	private String type;
	/**
	 * 設備型號
	 */
	private String model;
	/**
	 * 設備品牌
	 */
	private String brand;
	/**
	 * 設備編號
	 */
	private String machineNo;
	/**
	 * 加工精度
	 */
	private String machineAccuracy;
	/**
	 * 運行總時間
	 */
	private String runTotalTime;
	/**
	 * 運行時長
	 */
	private String runTime;
	/**
	 * 產出顯示
	 */
	private String outputDisplay;
	/**
	 * 設備稼動率
	 */
	private String utilizationRate;
	/**
	 * 設備及時狀態
	 */
	private String machinTimelyStatus;
	/**
	 * 設備狀態顯示
	 */
	private String machinStatusDisplay;
	/**
	 * 保養週期顯示
	 */
	private String machinIntervalDisplay;
	/**
	 * 日期
	 */
	private Date dataTime;
	/**
	 * 插入时间
	 */
	private Date createTime;

}
