package com.xiao.gs.data.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@ApiModel(description = "用户")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class User extends AbstractEntity {

    @ApiModelProperty(value = "用户名")
    @Column(unique = true)
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;

    protected User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ApiModelProperty(value = "用户Id", required = true, example = "1")
    @Override
    public Long getId() {
        return id;
    }

}
