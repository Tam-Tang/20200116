package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工维修标准工时表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 15:58:51
 */
@Data
@TableName("TH_REPAIR_STABDARD_TIME")
public class ThRepairStabdardTimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 员工编号
	 */
	private String userId;
	/**
	 * 员工编号
	 */
	private String userName;
	/**
	 * 数量
	 */
	private String num;

}
