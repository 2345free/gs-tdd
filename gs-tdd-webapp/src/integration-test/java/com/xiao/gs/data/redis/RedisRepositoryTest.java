package com.xiao.gs.data.redis;

import com.xiao.gs.AbstractIntegrationTest;
import com.xiao.gs.data.jpa.domain.Address;
import com.xiao.gs.data.redis.model.Person;
import com.xiao.gs.data.redis.repository.PersonRDSRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RedisRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PersonRDSRepository repo;

    @Test
    public void test1() {
        Person rand = Person.builder().firstname("al'thor").lastname("rand").address(
                new Address("street", "city", "country")
        ).build();

        repo.save(rand);
        assertThat(repo.findById(rand.getId())).isNotEmpty();

        assertThat(repo.count()).isEqualTo(1);

        repo.delete(rand);
        assertThat(repo.findById(rand.getId())).isEmpty();
    }

}
