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
package com.xiao.gs.data.repository;

import com.xiao.gs.data.domain.Customer;
import com.xiao.gs.data.domain.EmailAddress;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@link Repository} to access {@link Customer} instances.
 *
 * @author luoxiaoxiao
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

    @Override
    List<Customer> findAll();

    @Override
    @Transactional(timeout = 10, rollbackFor = {})
    <S extends Customer> S save(S entity);

    /**
     * Returns the customer with the given {@link EmailAddress}.
     *
     * @param emailAddress the {@link EmailAddress} to search for.
     * @return Customer
     */
    Customer findByEmailAddress(EmailAddress emailAddress);
}
