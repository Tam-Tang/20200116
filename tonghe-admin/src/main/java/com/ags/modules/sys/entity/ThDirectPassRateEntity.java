package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 直通率數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:11
 */
@Data
@TableName("TH_DIRECT_PASS_RATE")
public class ThDirectPassRateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 數據ID
	 */
	@TableId
	private String id;
	/**
	 * 直通率類型
	 */
	private String passType;
	/**
	 * 總數量
	 */
	private String totalNum;
	/**
	 * FAIL數量
	 */
	private String failNum;
	/**
	 * 直通率
	 */
	private String passRate;
	/**
	 * 日期
	 */
	private Date dataTime;
	/**
	 * 插入時間
	 */
	private Date createTime;

}
