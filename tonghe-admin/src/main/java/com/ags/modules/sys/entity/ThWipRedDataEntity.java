package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${comments}
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-12-02 15:41:23
 */
@Data
@TableName("TH_WIP_RED_DATA")
public class ThWipRedDataEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 數據ID
	 */
	@TableId
	private Long id;
	/**
	 * 站點ID
	 */
	private String stationId;
	/**
	 * WIP警戒值
	 */
	private String wipNum;

}
