//package com.xiao.gs.data.elasticsearch;
//
//import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
//import com.xiao.gs.AbstractIntegrationTest;
//import com.xiao.gs.data.elasticsearch.document.Customer;
//import com.xiao.gs.data.elasticsearch.repository.CustomerJestRepository;
//import org.junit.After;
//import org.junit.Ignore;
//import org.junit.Rule;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.rule.OutputCapture;
//
//import javax.annotation.PostConstruct;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author luoxiaoxiao
// * @date 2018-12-16 16:52
// */
//@Ignore
//public class JestTest extends AbstractIntegrationTest {
//
//    @Rule
//    public OutputCapture outputCapture = new OutputCapture();
//    @Autowired
//    private JestElasticsearchTemplate template;
//    @Autowired
//    private CustomerJestRepository repository;
//
//    @Test
//    public void testDefaultSettings() {
//        saveCustomers();
//        fetchAllCustomers();
//        fetchIndividualCustomers();
//        String output = this.outputCapture.toString();
//        assertThat(output).contains("firstName='Alice', lastName='Smith'");
//    }
//
//    @After
//    public void deleteIndex() {
//        template.deleteIndex(Customer.class);
//    }
//
//    @PostConstruct
//    public void insertDataSample() {
//        this.repository.deleteAll();
//    }
//
//    private void saveCustomers() {
//        this.repository.save(new Customer("Alice", "Smith"));
//        this.repository.save(new Customer("Bob", "Smith"));
//    }
//
//    private void fetchAllCustomers() {
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (Customer customer : this.repository.findAll()) {
//            System.out.println(customer);
//        }
//        System.out.println();
//    }
//
//    private void fetchIndividualCustomers() {
//        System.out.println("Customer found with findByFirstName('Alice'):");
//        System.out.println("--------------------------------");
//        System.out.println(this.repository.findByFirstName("Alice"));
//
//        System.out.println("Customers found with findByLastName('Smith'):");
//        System.out.println("--------------------------------");
//        for (Customer customer : this.repository.findByLastName("Smith")) {
//            System.out.println(customer);
//        }
//    }
//
//}
