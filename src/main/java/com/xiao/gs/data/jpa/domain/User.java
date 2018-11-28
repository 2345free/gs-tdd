package com.xiao.gs.data.jpa.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class User extends AbstractEntity {

    public static final long serialVersionUID = 1L;

    @Excel(name = "用户名")
    @Column(unique = true)
    private String username;

    @Excel(name = "密码")
    private String password;

    @Override
    public Long getId() {
        return id;
    }

}
