package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Food {
	private Integer fid; 
	private Integer tid; 
	private String fname; 
	private String fpic; 
	private Double fprice; 
	private Integer forder; 
	private String fdesc; 
	private String fregtime;

	@TableField(exist = false)
	private Ftype ftype;
}
