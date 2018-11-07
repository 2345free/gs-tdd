package com.xiao.gs.data.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author luoxiaoxiao
 */
@Data
@Entity
@Table(name = "TEST", schema = "PUBLIC", catalog = "TEST")
public class TestEntity {
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NAME")
    private String name;

}
