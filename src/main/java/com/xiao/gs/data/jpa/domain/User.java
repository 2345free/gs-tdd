package com.xiao.gs.data.jpa.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@ApiModel(description = "用户")
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class User extends AbstractEntity {

    public static final long serialVersionUID = 1L;

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
