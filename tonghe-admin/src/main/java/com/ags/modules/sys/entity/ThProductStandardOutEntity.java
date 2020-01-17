package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 產線標準產量表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-05 14:30:56
 */
@Data
@TableName("TH_PRODUCT_STANDARD_OUT")
public class ThProductStandardOutEntity implements Serializable {
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
	 * 標準產出數量
	 */
	private String num;
	/**
	 * 開始時間
	 */
	private Date startTime;
	/**
	 * 結束時間
	 */
	private Date endTime;

}
