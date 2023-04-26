package com.ocean.reggie.dto;

import com.ocean.reggie.entity.Setmeal;
import com.ocean.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
