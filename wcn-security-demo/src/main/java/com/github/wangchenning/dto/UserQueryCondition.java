package com.github.wangchenning.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryCondition {
    private String username;
    @ApiModelProperty(value = "用户年龄起始值", example = "17")
    private int age;
    @ApiModelProperty(value = "用户年龄终止值", example = "17")
    private int ageTo;
    private String xxx;

}
