package com.ocean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.List;

@Data
public class Ftype {
	private Integer tid;
	private String tname;

	@TableField(exist = false)
	private List<Food> foodList;
}
