package com.ags.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产线标准产出信息表
 * 
 * @author AGS
 * @email AGS@gmail.com
 * @date 2019-11-05 14:20:39
 */
@Data
@TableName("TH_PRODUCT_UPH")
public class ThProductUphEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据ID
	 */
	@TableId
	private Long id;
	/**
	 * 产线编号
	 */
	private String productLineId;
	/**
	 * 数量
	 */
	private String num;

}
