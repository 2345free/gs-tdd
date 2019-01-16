package com.xiao.gs.data.jpa.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base class to derive entity classes from.
 *
 * @author luoxiaoxiao
 */
@Data
@MappedSuperclass
public class AbstractEntity<ID extends Serializable> implements Persistable<ID>, Serializable {

    private static final long serialVersionUID = -7027476978800750136L;
    @Excel(name = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenerator")
    @SequenceGenerator(name = "mySeqGenerator", sequenceName = "mySequence", initialValue = 200, allocationSize = 10)
    protected ID id;

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

}
