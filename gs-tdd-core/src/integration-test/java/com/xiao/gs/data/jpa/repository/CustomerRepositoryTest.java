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
package com.xiao.gs.data.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.xiao.gs.data.AbstractJPAIntegrationTest;
import com.xiao.gs.data.jpa.domain.Customer;
import com.xiao.gs.data.jpa.domain.EmailAddress;
import com.xiao.gs.data.jpa.domain.QCustomer;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.data.domain.PageRequest.of;

/**
 * Integration tests for {@link CustomerRepository}.
 */
public class CustomerRepositoryTest extends AbstractJPAIntegrationTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void findsCustomerById() {
        Optional<Customer> optionalCustomer = repository.findById(1L);
        assertTrue(optionalCustomer.isPresent());
        Customer customer = optionalCustomer.get();
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
    }

    @Test
    public void findsAllCustomers() {
        List<Customer> customers = repository.findAll();
        assertThat(customers, hasSize(3));
    }

    @Test
    public void savesExistingCustomer() {
        Optional<Customer> optionalCustomer = repository.findById(1L);
        assertTrue(optionalCustomer.isPresent());
        Customer dave = optionalCustomer.get();
        dave.setEmailAddress(new EmailAddress("davematthews@dmband.com"));
        repository.save(dave);

        Optional<Customer> optionalResult = repository.findById(1L);
        assertTrue(optionalResult.isPresent());
        Customer result = optionalResult.get();

        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(notNullValue()));
        assertThat(result.getFirstname(), is("Dave"));
        assertThat(result.getEmailAddress(), is(new EmailAddress("davematthews@dmband.com")));
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
