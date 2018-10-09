package com.xiao.gs;

import com.xiao.gs.data.domain.User;
import com.xiao.gs.data.repository.UserRepository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRepositoryIntegrationTest extends AbstractIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void crudTest() {
        // save a couple of customers
        userRepository.save(new User("Bauer", "123"));
        userRepository.save(new User("Chloe", "123"));
        userRepository.save(new User("Bauer", "123"));
        userRepository.save(new User("David", "123"));
        userRepository.save(new User("Michelle Dessler", "123"));

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
        log.info("User found with findByUsername('luoxx'):");
        log.info("--------------------------------------------");
        User luoxx = userRepository.findByUsername("luoxx");
        log.info(luoxx.toString());
    }

}
