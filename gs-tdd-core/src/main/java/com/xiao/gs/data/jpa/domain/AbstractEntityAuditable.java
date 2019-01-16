package com.xiao.gs.data.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author luoxiaoxiao
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractEntityAuditable<ID extends Serializable> extends AbstractEntity<ID> {

    private static final long serialVersionUID = 7479587631743834284L;

    @CreatedDate
    private DateTime createdDate;
    @LastModifiedDate
    private DateTime lastModifiedDate;

}
