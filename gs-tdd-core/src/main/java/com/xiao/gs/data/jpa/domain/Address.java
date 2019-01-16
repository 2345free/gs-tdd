package com.xiao.gs.data.jpa.domain;

import lombok.*;

import javax.persistence.Entity;

/**
 * An address.
 *
 * @author luoxiaoxiao
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address extends AbstractEntityAuditable<Long> {

    private static final long serialVersionUID = -1184705370441387746L;

    private String street;

    private String city;

    private String country;

//    @Id
//    @Override
//    public Long getId() {
//        return id;
//    }

}
