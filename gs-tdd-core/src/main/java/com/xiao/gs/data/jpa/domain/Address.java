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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address extends AbstractEntity {

    private String street;

    private String city;

    private String country;

}
