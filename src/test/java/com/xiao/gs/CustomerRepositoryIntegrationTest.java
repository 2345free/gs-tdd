/*
 * Copyright 2012-2014 the original author or authors.
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
package com.xiao.gs;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.xiao.gs.data.jpa.domain.Customer;
import com.xiao.gs.data.jpa.domain.EmailAddress;
import com.xiao.gs.data.jpa.domain.QCustomer;
import com.xiao.gs.data.jpa.repository.CustomerRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.springframework.data.domain.PageRequest.of;

/**
 * Integration tests for {@link CustomerRepository}.
 */
public class CustomerRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void findsCustomerById() {

        Optional<Customer> customerOptional = repository.findById(1L);
        Customer customer = customerOptional.get();

        assertThat(customer, is(notNullValue()));
        assertThat(customer.getFirstname(), is("Dave"));
        assertThat(customer.getLastname(), is("Matthews"));
    }

    @Test
    public void savesNewCustomer() {
        Customer result = repository.save(Customer.builder().firstname("Stefan").lastname("Lassard").emailAddress(new EmailAddress("Stefan@gmail.com")).build());
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(notNullValue()));
        assertThat(result.getFirstname(), is("Stefan"));
        assertThat(result.getLastname(), is("Lassard"));

        // 删除此条数据
        Long id = result.getId();
        repository.deleteById(id);
        assertFalse(repository.findById(id).isPresent());
    }

    @Test
    public void findsAllCustomers() {

        List<Customer> customers = repository.findAll();
        assertThat(customers, hasSize(3));
    }

    @Test
    public void savesExistingCustomer() {

        Optional<Customer> customerOptional = repository.findById(1L);
        Customer dave = customerOptional.get();
        dave.setEmailAddress(new EmailAddress("davematthews@dmband.com"));
        repository.save(dave);

        Optional<Customer> resultOptional = repository.findById(1L);
        Customer result = resultOptional.get();

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(notNullValue()));
        assertThat(result.getFirstname(), is("Dave"));
        assertThat(result.getEmailAddress(), is(new EmailAddress("davematthews@dmband.com")));

        // 还原邮箱,后续测试有用到
        dave.setEmailAddress(new EmailAddress("dave@dmband.com"));
        repository.save(dave);
    }

    @Test
    public void findsCustomersByEmailAddress() {

        Customer result = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));

        assertThat(result, is(notNullValue()));
        assertThat(result.getFirstname(), is("Dave"));
        assertThat(result.getLastname(), is("Matthews"));
    }

    @Test
    public void accessesCustomersPageByPage() {

        Page<Customer> result = repository.findAll(of(1, 1));

        assertThat(result, is(notNullValue()));
        assertThat(result.isFirst(), is(false));
        assertThat(result.isLast(), is(false));
        assertThat(result.getNumberOfElements(), is(1));
    }

    @Test
    public void executesQuerydslPredicate() {

        Customer dave = repository.findByEmailAddress(new EmailAddress("dave@dmband.com"));
        Customer carter = repository.findByEmailAddress(new EmailAddress("carter@dmband.com"));

        QCustomer customer = QCustomer.customer;

        BooleanExpression firstnameStartsWithDa = customer.firstname.startsWith("Da");
        BooleanExpression lastnameContainsEau = customer.lastname.contains("eau");

        Iterable<Customer> result = repository.findAll(firstnameStartsWithDa.or(lastnameContainsEau));

        assertThat(result, is(Matchers.iterableWithSize(2)));
        assertThat(result, hasItems(dave, carter));
    }

    @Test
    public void deletesCustomer() {
        repository.deleteById(1L);
        assertFalse(repository.findById(1L).isPresent());
    }

}
