package com.xiao.gs.data.redis.model;

import com.xiao.gs.data.jpa.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author luoxiaoxiao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("people")
public class Person {

    @Id
    String id;

    String firstname;

    String lastname;

    Address address;

}
