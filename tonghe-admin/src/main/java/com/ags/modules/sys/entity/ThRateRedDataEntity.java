package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 達成率警戒值配置
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-02 15:41:24
 */
@Data
@TableName("TH_RATE_RED_DATA")
public class ThRateRedDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 數據ID
	 */
	@TableId
	private Long id;
	/**
	 * 產線編號
	 */
	private String lineId;
	/**
	 * 達成率警戒值(單位%)
	 */
	private String rateNum;


}
