//package com.xiao.gs.data.elasticsearch;
//
//import com.xiao.gs.AbstractIntegrationTest;
//import com.xiao.gs.data.elasticsearch.document.Conference;
//import com.xiao.gs.data.elasticsearch.repository.ConferenceRepository;
//import org.junit.After;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.geo.GeoPoint;
//import org.springframework.data.elasticsearch.core.query.Criteria;
//import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.assertThat;
//
//@Ignore
//public class ElasticsearchOperationsTest extends AbstractIntegrationTest {
//
//    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//    @Autowired
//    private ElasticsearchTemplate template;
//    @Autowired
//    private ConferenceRepository repository;
//
//    @Test
//    public void textSearch() throws ParseException {
//
//        String expectedDate = "2014-10-29";
//        String expectedWord = "java";
//        CriteriaQuery query = new CriteriaQuery(
//                new Criteria("keywords").contains(expectedWord).and(new Criteria("date").greaterThanEqual(expectedDate)));
//
//        List<Conference> result = template.queryForList(query, Conference.class);
//
//        assertThat(result, hasSize(3));
//
//        for (Conference conference : result) {
//            assertThat(conference.getKeywords(), hasItem(expectedWord));
//            assertThat(format.parse(conference.getDate()), greaterThan(format.parse(expectedDate)));
//        }
//    }
//
//    @Test
//    public void geoSpatialSearch() {
//
//        GeoPoint startLocation = new GeoPoint(50.0646501D, 19.9449799D);
//        String range = "330mi"; // or 530km
//        CriteriaQuery query = new CriteriaQuery(new Criteria("location").within(startLocation, range));
//
//        List<Conference> result = template.queryForList(query, Conference.class);
//
//        assertThat(result, hasSize(2));
//    }
//
//    @After
//    @PreDestroy
//    public void deleteIndex() {
//        template.deleteIndex(Conference.class);
//    }
//
//    @PostConstruct
//    public void insertDataSample() {
//
//        // Remove all documents
//        repository.deleteAll();
//        template.refresh(Conference.class);
//
//        // Save data sample
//        repository.save(Conference.builder().date("2014-11-06").name("Spring eXchange 2014 - London")
//                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(51.500152D, -0.126236D)).build());
//        repository.save(Conference.builder().date("2014-12-07").name("Scala eXchange 2014 - London")
//                .keywords(Arrays.asList("scala", "play", "java")).location(new GeoPoint(51.500152D, -0.126236D)).build());
//        repository.save(Conference.builder().date("2014-11-20").name("Elasticsearch 2014 - Berlin")
//                .keywords(Arrays.asList("java", "elasticsearch", "kibana")).location(new GeoPoint(52.5234051D, 13.4113999))
//                .build());
//        repository.save(Conference.builder().date("2014-11-12").name("AWS London 2014")
//                .keywords(Arrays.asList("cloud", "aws")).location(new GeoPoint(51.500152D, -0.126236D)).build());
//        repository.save(Conference.builder().date("2014-10-04").name("JDD14 - Cracow")
//                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(50.0646501D, 19.9449799)).build());
//    }
//
//}
