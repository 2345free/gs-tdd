package com.xiao.gs.data.jpa.repository;

import com.xiao.gs.data.AbstractJPAIntegrationTest;
import com.xiao.gs.data.jpa.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRepositoryTest extends AbstractJPAIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void crudTest() {
        // save a couple of customers
        userRepository.save(User.builder().username("Bauer").password("123").build());
        userRepository.save(User.builder().username("Chloe").password("123").build());
        userRepository.save(User.builder().username("David").password("123").build());

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        userRepository.findById(1L)
                .ifPresent(user -> {
                    log.info("User found with findById(1L):");
                    log.info("--------------------------------");
                    log.info(user.toString());
                    log.info("");
                });

        // fetch customers by name
        log.info("User found with findByUsername('Chloe'):");
        log.info("--------------------------------------------");
        User chloe = userRepository.findByUsername("Chloe");
        log.info(chloe.toString());
    }

}
