package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 單板WIP趨勢數據表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-28 10:44:09
 */
@Data
@TableName("TH_VENEER_WIP")
public class ThVeneerWipEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 數據ID
	 */
	@TableId
	private String id;
	/**
	 * WIP類型
	 */
	private String wipType;
	/**
	 * WIP數量
	 */
	private String wipNum;
	/**
	 * 日期
	 */
	private Date dataTime;
	/**
	 * 插入時間
	 */
	private Date createTime;

}
