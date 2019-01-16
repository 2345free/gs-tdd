package com.xiao.gs.data.jpa.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoxiaoxiao
 */
@Data
@MappedSuperclass
public abstract class AbstractEntityAuditable<ID extends Serializable> implements Persistable<ID>, Serializable {

    private static final long serialVersionUID = 7479587631743834284L;

    @Excel(name = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenerator")
    @SequenceGenerator(name = "mySeqGenerator", sequenceName = "mySequence", initialValue = 200, allocationSize = 10)
    protected ID id;
    @CreatedDate
    private DateTime createdDate;
    @LastModifiedDate
    private DateTime lastModifiedDate;

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

}
