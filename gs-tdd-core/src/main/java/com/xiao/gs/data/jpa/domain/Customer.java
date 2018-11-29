/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiao.gs.data.jpa.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A customer.
 *
 * @author luoxiaoxiao
 */
@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Entity
public class Customer extends AbstractEntity {

    private String firstname, lastname;

    @Column(unique = true)
    private EmailAddress emailAddress;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses = new HashSet<>();

    protected Customer() {

    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Adds the given {@link Address} to the {@link Customer}.
     *
     * @param address must not be {@literal null}.
     */
    public void add(Address address) {
        Assert.notNull(address, "address can't be null");
        this.addresses.add(address);
    }

}
