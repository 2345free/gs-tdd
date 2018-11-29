package com.xiao.gs.data.jpa.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base class to derive entity classes from.
 *
 * @author luoxiaoxiao
 */
@Data
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Excel(name = "编号")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenerator")
    @SequenceGenerator(name = "mySeqGenerator", sequenceName = "mySequence", initialValue = 200, allocationSize = 10)
    protected Long id;

}
