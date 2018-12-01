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
package com.xiao.gs.data;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Abstract integration test to populate the database with dummy data.
 */
@ActiveProfiles("test")
@Rollback
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AbstractJPAIntegrationTest.JPATestConfig.class)
public abstract class AbstractJPAIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Before
    public void initData() {
        // org.hibernate.tool.schema.internal.SchemaCreatorImpl 会自动导入import.sql,无需执行下面的手动导入
        // ScriptUtils.executeSqlScript(DataSourceUtils.getConnection(dataSource), new ClassPathResource("import.sql"));
    }

    @Profile("test")
    @TestConfiguration
    @EnableJpaRepositories(basePackages = "com.xiao.gs.data.jpa.repository")
    public static class JPATestConfig {

        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
        }

        @Bean
        public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
            jpaVendorAdapter.setDatabase(Database.H2);
            jpaVendorAdapter.setGenerateDdl(true);
            jpaVendorAdapter.setShowSql(true);
            return jpaVendorAdapter;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
            lemfb.setDataSource(dataSource());
            lemfb.setJpaVendorAdapter(jpaVendorAdapter());
            lemfb.setPackagesToScan("com.xiao.gs.data.jpa.domain");
            Map<String, Object> jpaProps = Maps.newHashMapWithExpectedSize(1);
            jpaProps.put("hibernate.hbm2ddl.auto", "create-drop");
            lemfb.setJpaPropertyMap(jpaProps);
            return lemfb;
        }

    }

}
