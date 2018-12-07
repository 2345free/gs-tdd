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
@NoArgsConstructor
@Entity
public class Customer extends AbstractEntity {

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private EmailAddress emailAddress;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses = new HashSet<>();

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
